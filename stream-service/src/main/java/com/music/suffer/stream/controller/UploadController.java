package com.music.suffer.stream.controller;

import com.music.suffer.stream.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @PostMapping(path = "/music", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestParam("musicId") Long musicId,
            @RequestParam("file") MultipartFile file
    ) {
        uploadService.uploadFile(musicId, file);
        return ResponseEntity.ok("Upload successful");
    }
}
