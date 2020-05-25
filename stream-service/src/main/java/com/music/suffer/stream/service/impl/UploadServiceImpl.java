package com.music.suffer.stream.service.impl;

import com.music.suffer.stream.domain.MusicFile;
import com.music.suffer.stream.exception.FileUploadingException;
import com.music.suffer.stream.repository.MusicFileRepository;
import com.music.suffer.stream.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final MusicFileRepository repository;
    @Value("${file.rootPath}")
    private String rootPath;

    @Override
    public void uploadFile(Long musicId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Empty file!");
        }
        UUID id = UUID.randomUUID();
        String filePath = saveFile(file, id);
        MusicFile musicFile = new MusicFile()
                .setId(id)
                .setFilePath(filePath)
                .setMusicId(musicId);
        repository.save(musicFile);
    }

    private String saveFile(MultipartFile file, UUID id) {
        File destination = new File(rootPath);
        if (!destination.exists()) {
            destination.mkdir();
        }

        String filename = id.toString() + "." + file.getOriginalFilename();
        try {
            file.transferTo(new File(rootPath + "/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadingException("Unable to upload file.");
        }

        return filename;
    }
}
