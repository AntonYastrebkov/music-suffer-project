package com.music.suffer.admin.domain.dto;

import com.music.suffer.admin.domain.model.MusicGenre;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlbumDTO {
    private Long id;
    private String name;
    private long artistId;
    private int year;
    private List<MusicDTO> compositions;
    private MusicGenre genre;
}
