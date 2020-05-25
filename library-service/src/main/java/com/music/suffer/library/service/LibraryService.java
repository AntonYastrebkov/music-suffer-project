package com.music.suffer.library.service;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.Artist;
import com.music.suffer.library.domain.entity.MusicGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibraryService {
    Album getAlbum(Long id);

    Page<Album> getAlbumList(String filter, Pageable pageable);

    Page<Album> getAlbumListByGenre(MusicGenre genre, Pageable pageable);

    Artist getArtist(Long id);

    Page<Artist> getArtistList(String filter, Pageable pageable);
}
