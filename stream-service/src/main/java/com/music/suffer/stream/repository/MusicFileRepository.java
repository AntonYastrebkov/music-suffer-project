package com.music.suffer.stream.repository;

import com.music.suffer.stream.domain.MusicFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MusicFileRepository extends JpaRepository<MusicFile, UUID> {
}
