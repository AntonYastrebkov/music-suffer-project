package com.music.suffer.library.controller;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.Artist;
import com.music.suffer.library.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/artist/{id}")
    public Artist getArtist(@PathVariable Long id) {
        return artistService.getArtist(id);
    }

    @GetMapping("/artist/{id}/albums")
    public Page<Album> getAlbums(
            @PathVariable Long id,
            @PageableDefault(sort = {"id"}, size = 9, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return artistService.getAlbumsByArtist(id, pageable);
    }
}
