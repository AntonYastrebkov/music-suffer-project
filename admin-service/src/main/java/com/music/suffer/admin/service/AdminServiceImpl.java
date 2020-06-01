package com.music.suffer.admin.service;

import com.music.suffer.admin.domain.dto.AlbumDTO;
import com.music.suffer.admin.domain.dto.ArtistDTO;
import com.music.suffer.admin.domain.dto.MusicDTO;
import com.music.suffer.admin.domain.entity.Album;
import com.music.suffer.admin.domain.entity.Artist;
import com.music.suffer.admin.domain.entity.Music;
import com.music.suffer.admin.domain.model.MusicDataResponse;
import com.music.suffer.admin.repository.AlbumRepository;
import com.music.suffer.admin.repository.ArtistRepository;
import com.music.suffer.admin.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
    private final ModelMapper modelMapper;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public Artist saveArtist(ArtistDTO artistDTO, MultipartFile image) {
        Artist artistToSave = modelMapper.map(artistDTO, Artist.class);
        artistToSave.setIsDeleted(false);
        if (!image.isEmpty()) {
            artistToSave.setImagePath(saveImage(image));
        }
        return artistRepository.save(artistToSave);
    }

    @Override
    public Artist updateArtist(ArtistDTO artistDTO, MultipartFile image) {
        Artist artist = artistRepository.findById(artistDTO.getId()).orElseThrow(() ->
                new EntityNotFoundException("Artist with id " + artistDTO.getId() + " not found"));
        artist.setName(artistDTO.getName());
        artist.setBiography(artistDTO.getBiography());
        artist.setCountry(artistDTO.getCountry());
        if (!image.isEmpty()) {
            artist.setImagePath(saveImage(image));
        }
        return artist;
    }

    @Override
    public List<Artist> findArtistByName(String name) {
        return artistRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<MusicDataResponse> saveAlbum(AlbumDTO albumDTO, MultipartFile image) {

        Album albumToSave = modelMapper.map(albumDTO, Album.class);
        albumToSave.setIsDeleted(false);

        Artist artist = artistRepository.findById(albumDTO.getArtistId()).orElseThrow(() ->
                new EntityNotFoundException("Artist with id " + albumDTO.getArtistId() + " not found."));
        if (!image.isEmpty()) {
            albumToSave.setCoverPath(saveImage(image));
        }
        Album savedAlbum = albumRepository.save(albumToSave);
        return albumToSave.getCompositions().stream()
                .peek(m -> m.setArtist(artist))
                .peek(m -> m.setGenre(albumDTO.getGenre()))
                .peek(m -> m.setYear(albumDTO.getYear()))
                .peek(m -> m.setAlbum(savedAlbum))
                .map(musicRepository::save)
                .map(m -> new MusicDataResponse()
                        .setId(m.getId())
                        .setPosition(m.getPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public Album updateAlbum (AlbumDTO albumDTO, MultipartFile image) {
        Album album = albumRepository.findById(albumDTO.getId()).orElseThrow(() ->
                new EntityNotFoundException("Album with id " + albumDTO.getId() + " not found"));
        album.setName(albumDTO.getName());
        album.setGenre(albumDTO.getGenre());
        album.setYear(albumDTO.getYear());
        album.setArtist(artistRepository.findById(albumDTO.getArtistId()).orElseThrow(() ->
                new EntityNotFoundException("Artist with id " + albumDTO.getArtistId() + " not found")));
        if (!image.isEmpty()) {
            album.setCoverPath(saveImage(image));
        }
        return album;
    }

    @Override
    public List<MusicDataResponse> addComposition(MusicDTO musicDTO) {
        return saveComposition(musicDTO.getAlbumId(), musicDTO);
    }

    @Override
    public List<MusicDataResponse> saveComposition(Long albumId, MusicDTO musicDTO) {
        Music musicToSave = modelMapper.map(musicDTO, Music.class);
        musicToSave.setAlbum(albumRepository.findById(albumId).orElseThrow(() ->
                new EntityNotFoundException("Album with id " + albumId + " not found")));
        musicToSave.setArtist(artistRepository.findById(musicDTO.getArtistId()).orElseThrow(() ->
                        new EntityNotFoundException("Artist with id " + musicDTO.getArtistId() + " not found")));
        Music savedMusic = musicRepository.save(musicToSave);
        return Collections.singletonList(new MusicDataResponse()
                .setId(savedMusic.getId())
                .setPosition(savedMusic.getPosition()));
    }

    @Override
    public List<MusicDataResponse> updateComposition(MusicDTO musicDTO) {
        Music music = musicRepository.findById(musicDTO.getId()).orElseThrow(() ->
                new EntityNotFoundException("Composition with id " + musicDTO.getId() + " not found"));
        music.setName(musicDTO.getName());
        music.setGenre(musicDTO.getGenre());
        music.setYear(musicDTO.getYear());
        return Collections.singletonList(new MusicDataResponse()
                .setId(music.getId())
                .setPosition(music.getPosition()));
    }

    private String saveImage(MultipartFile file) {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File destination = new File(uploadPath);
            if (!destination.exists()) {
                destination.mkdir();
            }

            String filename = UUID.randomUUID().toString() + "."
                    + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" + filename));
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }

            return filename;
        } else {
            return "";
        }
    }
}
