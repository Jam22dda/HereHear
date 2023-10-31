package com.ssafy.herehear.report.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import com.ssafy.herehear.report.dto.request.ReportReqDto;
import com.ssafy.herehear.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("")
    public CommonResponse report(@RequestBody ReportReqDto reportReqDto) {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[신고하기] memberId: {}, reportReqDto: {}, time: {}", memberId, reportReqDto, TimeFormatUtil.formatTime(LocalDateTime.now()));

        reportService.report(memberId, reportReqDto);

        return new CommonResponse("200", "신고 등록");
    }

}
