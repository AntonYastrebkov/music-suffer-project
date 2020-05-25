package com.music.suffer.stream.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class MusicFile {
    @Id
    private UUID id;
    @NotNull
    private Long musicId;
    @NotBlank
    private String filePath;
}
