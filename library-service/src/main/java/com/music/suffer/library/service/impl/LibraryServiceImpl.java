package com.music.suffer.library.service.impl;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.Artist;
import com.music.suffer.library.domain.entity.MusicGenre;
import com.music.suffer.library.domain.model.AlbumDTO;
import com.music.suffer.library.repository.AlbumRepository;
import com.music.suffer.library.repository.ArtistRepository;
import com.music.suffer.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryServiceImpl implements LibraryService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Override
    public AlbumDTO getAlbum(Long id) {
        Album album = albumRepository.findById(id)
                .map(a -> {
                    if (a.getIsDeleted()) {
                        throw new EntityNotFoundException("Album with id " + id + " not found");
                    } else {
                        return a;
                    }
                })
                .orElseThrow(() ->
                    new EntityNotFoundException("Album with id " + id + " not found"));

        return new AlbumDTO()
                .setId(album.getId())
                .setName(album.getName())
                .setArtist(album.getArtist())
                .setAverageScore(album.getAverageScore())
                .setCompositions(album.getCompositions())
                .setGenre(album.getGenre())
                .setYear(album.getYear())
                .setCoverPath(album.getCoverPath());
    }

    @Override
    public Page<Album> getAlbumList(String filter, Pageable pageable) {
        if (StringUtils.hasText(filter)) {
            return albumRepository.findByNameIgnoreCaseContaining(filter, pageable);
        } else {
            return albumRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Album> getAlbumListByGenre(MusicGenre genre, Pageable pageable) {
        return albumRepository.findByGenre(genre, pageable);
    }

    @Override
    public Artist getArtist(Long id) {
        return artistRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Artist with id " + id + " not found"));
    }

    @Override
    public Page<Artist> getArtistList(String filter, Pageable pageable) {
        if (StringUtils.hasText(filter)) {
            return artistRepository.findByNameIgnoreCaseContaining(filter, pageable);
        } else {
            return artistRepository.findAll(pageable);
        }
    }
}
