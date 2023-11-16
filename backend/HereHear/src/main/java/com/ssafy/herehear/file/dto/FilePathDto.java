package com.ssafy.herehear.file.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FilePathDto {

    private String filePath;

    @Builder
    public FilePathDto(String filePath) {
        this.filePath = filePath;
    }
}
