package com.ssafy.herehear.report.service;

import com.ssafy.herehear.report.dto.request.ReportReqDto;

public interface ReportService {
    void report(Long memberId, ReportReqDto reportReqDto);
}
