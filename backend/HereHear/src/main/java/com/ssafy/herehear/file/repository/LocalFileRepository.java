package com.ssafy.herehear.file.repository;

import com.ssafy.herehear.entity.LocalFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalFileRepository extends JpaRepository<LocalFile, Long> {

    Optional<LocalFile> findBySavedFileNameContaining(String fileName);

}
