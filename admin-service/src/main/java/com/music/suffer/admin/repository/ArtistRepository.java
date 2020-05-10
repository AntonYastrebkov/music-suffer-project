package com.music.suffer.admin.repository;

import com.music.suffer.admin.domain.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
