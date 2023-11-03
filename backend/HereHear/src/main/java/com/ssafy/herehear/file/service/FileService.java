package com.ssafy.herehear.file.service;

import com.ssafy.herehear.file.dto.FilePathDto;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FilePathDto saveFile(MultipartFile file) throws IOException;

    ResponseEntity<Resource> getFile(String fileName);
}
