package com.music.suffer.stream.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void uploadFile(Long musicId, MultipartFile file);
}
