package com.music.suffer.admin.controller;

import com.music.suffer.admin.domain.dto.AlbumDTO;
import com.music.suffer.admin.domain.dto.ArtistDTO;
import com.music.suffer.admin.domain.dto.MusicDTO;
import com.music.suffer.admin.domain.entity.Album;
import com.music.suffer.admin.domain.entity.Artist;
import com.music.suffer.admin.domain.model.MusicDataResponse;
import com.music.suffer.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EditController {
    private final AdminService adminService;

    @PutMapping(path = "/album/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Album editAlbum(
            @RequestParam("album") AlbumDTO albumDTO,
            @RequestParam("image") MultipartFile image
    ) {
        return adminService.updateAlbum(albumDTO, image);
    }

    @PutMapping(path = "/artist/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Artist editArtist(
            @RequestParam("artist") ArtistDTO artistDTO,
            @RequestParam("image") MultipartFile image
    ) {
        return adminService.updateArtist(artistDTO, image);
    }

    @PutMapping(path = "/album/{album_id}/music/id")
    public List<MusicDataResponse> editComposition(
            @RequestBody MusicDTO musicDTO
    ) {
        return adminService.updateComposition(musicDTO);
    }

    @PostMapping(path = "/album/{album_id}/music", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<MusicDataResponse> addComposition(
            @RequestBody MusicDTO musicDTO,
            @PathVariable("album_id") Long albumId
    ) {
        musicDTO.setAlbumId(albumId);
        return adminService.addComposition(musicDTO);
    }
}
