package com.music.suffer.admin.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music.suffer.admin.domain.model.MusicGenre;
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
    private Long id;
    private String name;
    private String coverPath;
    private int year;
    private int votes;
    private double averageScore;
    @OneToMany(mappedBy = "album")
    private List<Music> compositions;
    @Enumerated(EnumType.STRING)
    private MusicGenre genre;
    @ManyToOne(targetEntity = Artist.class)
    @JoinColumn(name = "artist_id")
    @JsonIgnore
    private Artist artist;

    @JsonProperty(value = "artistId", access = JsonProperty.Access.READ_ONLY)
    public Long getArtistId() {
        return artist.getId();
    }
}
