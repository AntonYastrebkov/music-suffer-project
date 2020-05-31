package com.music.suffer.library.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@NoArgsConstructor
public class Music {
    @Id
    @SequenceGenerator(name = "music_id_generator", sequenceName = "music_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "music_id_generator")
    private long id;
    private String name;
    private int position;
    private int year;
//    private long addedBy;
    @Enumerated(EnumType.STRING)
    private MusicGenre genre;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;
    @ManyToOne(targetEntity = Artist.class)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    private int listening;
}
