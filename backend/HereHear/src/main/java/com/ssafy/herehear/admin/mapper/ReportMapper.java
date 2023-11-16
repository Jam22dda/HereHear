package com.ssafy.herehear.admin.mapper;

import com.ssafy.herehear.admin.dto.ReportDto;
import com.ssafy.herehear.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", implementationName = "adminReportMapper")
public interface ReportMapper {

    @Mapping(source = "report.reportId", target = "reportId")
    @Mapping(source = "report.content", target = "content")
    @Mapping(source = "report.createTime", target = "createTime")
    @Mapping(source = "report.member.memberId", target = "memberId")
    @Mapping(source = "report.registeredMusic.registeredMusicId", target = "registeredMusicId")
    ReportDto toReportDto(Report report);

}
