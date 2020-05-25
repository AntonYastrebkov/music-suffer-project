package com.music.suffer.library.repository;

import com.music.suffer.library.domain.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Page<Artist> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
