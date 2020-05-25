package com.music.suffer.library.service.impl;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.Artist;
import com.music.suffer.library.repository.AlbumRepository;
import com.music.suffer.library.repository.ArtistRepository;
import com.music.suffer.library.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    @Override
    public Artist getArtist(Long id) {
        return artistRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Artist with id " + id + " not found"));
    }

    @Override
    public Page<Album> getAlbumsByArtist(Long artistId, Pageable pageable) {
        return albumRepository.findByArtistId(artistId, pageable);
    }
}
