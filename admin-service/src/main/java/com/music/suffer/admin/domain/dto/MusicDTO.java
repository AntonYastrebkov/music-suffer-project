package com.music.suffer.admin.domain.dto;

import com.music.suffer.admin.domain.model.MusicGenre;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicDTO {
    private Long id;
    private String name;
    private Long albumId;
    private int position;
    private Long artistId;
    private MusicGenre genre;
    private int year;
}
