package com.music.suffer.library.service;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistService {
    Artist getArtist(Long id);

    Page<Album> getAlbumsByArtist(Long artistId, Pageable pageable);
}
