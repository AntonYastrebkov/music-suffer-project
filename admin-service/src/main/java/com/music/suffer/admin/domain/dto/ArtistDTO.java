package com.music.suffer.admin.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ArtistDTO {
    private Long id;
    @NotBlank
    private String name;
    private String biography;
    @NotBlank
    private String country;
    private boolean isGroup;
}
