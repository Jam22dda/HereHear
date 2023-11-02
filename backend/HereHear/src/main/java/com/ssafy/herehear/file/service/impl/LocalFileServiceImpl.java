package com.ssafy.herehear.file.service.impl;

import com.ssafy.herehear.entity.LocalFile;
import com.ssafy.herehear.file.dto.FilePathDto;
import com.ssafy.herehear.file.repository.LocalFileRepository;
import com.ssafy.herehear.file.service.FileService;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalFileServiceImpl implements FileService {

    private final LocalFileRepository localFileRepository;

    @Value("${localFileService.file.path}")
    private String BASE_FILE_PATH;

    @Override
    public FilePathDto saveFile(MultipartFile file) throws IOException {
        if (file == null) {
            throw new CustomException(ExceptionStatus.FILE_NOT_FOUND);
        }

        if (!file.isEmpty()) {
            String today = TimeFormatUtil.YMDTimeFormat(LocalDateTime.now());
            String saveFolder = BASE_FILE_PATH + File.separator + today;

            // 폴더가 없다면 저장경로의 폴더를 생성
            File folder = new File(saveFolder);
            if (!folder.exists()) {
                if (folder.mkdirs()) log.info("{} 폴더 생성 성공", BASE_FILE_PATH + File.separator + today);
                else log.error("{} 폴더 생성 실패", BASE_FILE_PATH + File.separator + today);
            }

            String originalFileName = file.getOriginalFilename();
            if (!originalFileName.isEmpty()) {
                // 파일 저장
                String saveFileName = UUID.randomUUID().toString().replace("-", "")
                        + originalFileName.substring(originalFileName.lastIndexOf('.'));

                file.transferTo(new File(folder, saveFileName));

                localFileRepository.save(LocalFile.builder()
                        .savedFilePath(BASE_FILE_PATH + File.separator + today + File.separator + saveFileName)
                        .originalFileName(originalFileName)
                        .savedFileName(saveFileName)
                        .build());

                return FilePathDto.builder().filePath(saveFileName).build();
            }
        }

        throw new CustomException(ExceptionStatus.FILE_UPLOAD_FAILED);
    }

    @Override
    public ResponseEntity<Resource> getFile(String fileName) {
        LocalFile savedFileEntity = localFileRepository.findBySavedFileName(fileName)
                .orElseThrow(() -> new CustomException(ExceptionStatus.FILE_NOT_FOUND));

        // 다운로드할 이미지 파일의 경로 생성
        Path filePath = Paths.get(savedFileEntity.getSavedFilePath());

        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(ExceptionStatus.FILE_NOT_FOUND);
        }

        // 이미지 파일의 MIME 타입 지정
        // 이미지의 Content-Type을 확인하여 적절한 MIME 타입을 지정합니다.
        String contentType = "image/jpeg"; // 예시로 jpeg 이미지를 사용합니다.
        if (fileName.endsWith(".png")) {
            contentType = "image/png";
        } else if (fileName.endsWith(".gif")) {
            contentType = "image/gif";
        } else if (fileName.endsWith(".jpg")) {
            contentType = "image/jpg";
        }

        // 다운로드할 이미지 파일의 HTTP 헤더 설정
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
