package com.music.suffer.admin.service;

import com.music.suffer.admin.domain.dto.AlbumDTO;
import com.music.suffer.admin.domain.dto.ArtistDTO;
import com.music.suffer.admin.domain.dto.MusicDTO;
import com.music.suffer.admin.domain.entity.Album;
import com.music.suffer.admin.domain.entity.Artist;
import com.music.suffer.admin.domain.model.MusicDataResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {
    Artist saveArtist(ArtistDTO artistDTO, MultipartFile image);

    Artist updateArtist(ArtistDTO artistDTO, MultipartFile image);

    List<Artist> findArtistByName(String name);

    List<MusicDataResponse> saveAlbum(AlbumDTO albumDTO, MultipartFile image);

    Album updateAlbum(AlbumDTO albumDTO, MultipartFile image);

    List<MusicDataResponse> addComposition(MusicDTO musicDTO);

    List<MusicDataResponse> saveComposition(Long albumId, MusicDTO musicDTO);

    List<MusicDataResponse> updateComposition(MusicDTO musicDTO);
}
