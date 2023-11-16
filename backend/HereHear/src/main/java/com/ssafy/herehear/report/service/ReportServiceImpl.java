package com.ssafy.herehear.report.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.entity.Report;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.report.dto.request.ReportReqDto;
import com.ssafy.herehear.report.repository.RegisteredMusicRepository;
import com.ssafy.herehear.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final RegisteredMusicRepository registeredMusicRepository;

    @Override
    public void report(Long memberId, ReportReqDto reportReqDto) {
        Member member = MemberUtil.findMember(memberId);

        RegisteredMusic registeredMusic = registeredMusicRepository.findById(reportReqDto.getRegisteredMusicId())
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND_MUSIC));

        reportRepository.save(Report.builder()
                .member(member)
                .content(reportReqDto.getContent())
                .registeredMusic(registeredMusic)
                .build());
    }
}
