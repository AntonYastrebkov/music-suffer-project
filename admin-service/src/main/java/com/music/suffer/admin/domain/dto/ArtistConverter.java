package com.music.suffer.admin.domain.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistConverter implements Converter<String, ArtistDTO> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public ArtistDTO convert(String source) {
        return objectMapper.readValue(source, ArtistDTO.class);
    }
}
