package com.music.suffer.admin.controller;

import com.music.suffer.admin.domain.dto.AlbumDTO;
import com.music.suffer.admin.domain.dto.ArtistDTO;
import com.music.suffer.admin.domain.dto.MusicDTO;
import com.music.suffer.admin.domain.entity.Artist;
import com.music.suffer.admin.domain.model.MusicDataResponse;
import com.music.suffer.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreateController {
    private final AdminService adminService;

    @PostMapping(value = "/artist", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Artist createArtist(
            @RequestParam(name = "artist") @Valid ArtistDTO artistDTO,
            @RequestParam(name = "image", required = false) MultipartFile image
    ) {
        return adminService.saveArtist(artistDTO, image);
    }

    @GetMapping("/artist")
    public List<Artist> getArtistByName(@RequestParam String name) {
        if (StringUtils.hasText(name)) {
            return adminService.findArtistByName(name);
        }
        return Collections.emptyList();
    }

    @PostMapping(value = "/album", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<MusicDataResponse> createAlbum(
            @RequestParam(name = "album") @Valid AlbumDTO albumDTO,
            @RequestParam(name = "image", required = false) MultipartFile image
    ) {
        return adminService.saveAlbum(albumDTO, image);
    }

    @PostMapping(value = "/music", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<MusicDataResponse> createMusic(
            @RequestBody @Valid MusicDTO musicDTO
    ) {
        return adminService.saveComposition(0L, musicDTO);
    }
}
