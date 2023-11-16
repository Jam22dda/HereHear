package com.ssafy.herehear.admin.repository;

import com.ssafy.herehear.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminReportRepository extends JpaRepository<Report, Long> {
}
