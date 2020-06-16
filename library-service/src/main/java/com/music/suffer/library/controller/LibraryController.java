package com.music.suffer.library.controller;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.Artist;
import com.music.suffer.library.domain.entity.MusicGenre;
import com.music.suffer.library.domain.model.AlbumDTO;
import com.music.suffer.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/album")
    public Page<Album> getAlbumPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 12) Pageable pageable
    ) {
        return libraryService.getAlbumList(filter, pageable);
    }

    @GetMapping("/album/{id}")
    public AlbumDTO getAlbum(@PathVariable Long id) {
        return libraryService.getAlbum(id);
    }

    @GetMapping("/artist")
    public Page<Artist> getArtistPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 12) Pageable pageable
    ) {
        return libraryService.getArtistList(filter, pageable);
    }

    @GetMapping("/genre")
    public List<MusicGenre> getGenres() {
        return Arrays.asList(MusicGenre.values());
    }

    @GetMapping("/genre/{genre}")
    public Page<Album> getAlbumListByGenre(
            @PathVariable MusicGenre genre,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 12) Pageable pageable
    ) {
        return libraryService.getAlbumListByGenre(genre, pageable);
    }
}
