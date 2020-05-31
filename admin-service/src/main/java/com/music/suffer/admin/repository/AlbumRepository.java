package com.music.suffer.admin.repository;

import com.music.suffer.admin.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
