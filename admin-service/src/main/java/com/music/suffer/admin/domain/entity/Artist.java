package com.music.suffer.admin.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@NoArgsConstructor
public class Artist {
    @Id
    @SequenceGenerator(name = "artist_id_generator", sequenceName = "artist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_generator")
    private Long id;
    private String name;
    @Lob
    private String biography;
    private boolean isGroup;
    private String country;
    private String imagePath;
    private Boolean isDeleted;
}
