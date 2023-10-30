package com.ssafy.herehear.report.repository;

import com.ssafy.herehear.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
