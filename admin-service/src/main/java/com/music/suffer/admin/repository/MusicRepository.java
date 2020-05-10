package com.music.suffer.admin.repository;

import com.music.suffer.admin.domain.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
