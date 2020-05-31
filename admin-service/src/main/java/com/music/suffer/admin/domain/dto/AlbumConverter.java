package com.music.suffer.admin.domain.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumConverter implements Converter<String, AlbumDTO> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public AlbumDTO convert(String source) {
        return objectMapper.readValue(source, AlbumDTO.class);
    }
}
