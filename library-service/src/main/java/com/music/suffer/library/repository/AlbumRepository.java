package com.music.suffer.library.repository;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.MusicGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Page<Album> findByNameIgnoreCaseContaining(String name, Pageable pageable);

    Page<Album> findByGenre(MusicGenre genre, Pageable pageable);

    Page<Album> findByArtistId(Long artistId, Pageable pageable);
}
