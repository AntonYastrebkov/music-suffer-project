package com.music.suffer.library.domain.model;

import com.music.suffer.library.domain.entity.Artist;
import com.music.suffer.library.domain.entity.Music;
import com.music.suffer.library.domain.entity.MusicGenre;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AlbumDTO {
    private long id;
    private String name;
    private String coverPath;
    private int year;
    private Double averageScore;
    private Integer votes;
    private List<Music> compositions;
    private MusicGenre genre;
    private Artist artist;
}
