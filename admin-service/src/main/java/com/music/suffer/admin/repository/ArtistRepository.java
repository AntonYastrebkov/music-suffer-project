package com.music.suffer.admin.repository;

import com.music.suffer.admin.domain.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByNameIgnoreCaseContaining(String name);
}
