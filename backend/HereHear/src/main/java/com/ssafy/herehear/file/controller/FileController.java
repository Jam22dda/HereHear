package com.ssafy.herehear.file.controller;

import com.ssafy.herehear.file.dto.FilePathDto;
import com.ssafy.herehear.file.service.FileService;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public DataResponse<FilePathDto> upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("[파일 업로드] time: {}", TimeFormatUtil.formatTime(LocalDateTime.now()));

        FilePathDto filePathDto = fileService.saveFile(file);

        return new DataResponse<>("200", "파일 업로드 성공", filePathDto);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> getFishSkin(@PathVariable String fileName) {
        log.info("[파일 다운로드] time: {}", TimeFormatUtil.formatTime(LocalDateTime.now()));

        return fileService.getFile(fileName);
    }

}
