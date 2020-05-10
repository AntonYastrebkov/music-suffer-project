package com.music.suffer.library.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Album {
    @Id
    @SequenceGenerator(name = "album_id_generator", sequenceName = "album_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_id_generator")
    private long id;
    private String name;
    private String coverPath;
    private int year;
    private double averageScore;
    @OneToMany(mappedBy = "album")
    private List<Music> compositions;
    @Enumerated(EnumType.STRING)
    private MusicGenre genre;
    @ManyToOne(targetEntity = Artist.class)
    @JoinColumn(name = "artist_id")
    private Artist artist;
}