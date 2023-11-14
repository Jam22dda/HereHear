package com.ssafy.herehear.statistics.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.statistics.dto.response.PersonalHearTimeResDto;
import com.ssafy.herehear.statistics.dto.response.PersonalTagsResDto;
import com.ssafy.herehear.statistics.service.PersonalStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class PersonalStatisticsController {
    private final PersonalStatisticsService personalStatisticsService;

    @GetMapping("/likecount")
    public DataResponse<Integer> getLikeCount(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        int likeCount = personalStatisticsService.getLikeCount(memberId);
        return new DataResponse<>("200", "전체 좋아요 갯수 조회에 성공하였습니다.", likeCount);
    }

    @GetMapping("/heartime")
    public DataResponse<PersonalHearTimeResDto> getHearTime(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        PersonalHearTimeResDto personalHearTimeResDto = personalStatisticsService.getHearTime(memberId);
        return new DataResponse<>("200", "가장 많이 듣는 시간대 통계 조회에 성공하였습니다.", personalHearTimeResDto);
    }

    @GetMapping("/tags")
    public DataResponse<PersonalTagsResDto> getPersonalTags(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        PersonalTagsResDto personalTagsResDto = personalStatisticsService.getPersonalTags(memberId);
        return new DataResponse<>("200", "개인 태그 개수 조회에 성공하였습니다.", personalTagsResDto);
    }

    @GetMapping("/likecount/{memberId}")
    public DataResponse<Integer> getYourLikeCount(@RequestParam("memberId") long memberId) {
        int likeCount = personalStatisticsService.getLikeCount(memberId);
        return new DataResponse<>("200", "타인의 전체 좋아요 갯수 조회에 성공하였습니다.", likeCount);
    }

    @GetMapping("/heartime/{memberId}")
    public DataResponse<PersonalHearTimeResDto> getYourHearTime(@RequestParam("memberId") long memberId) {
        PersonalHearTimeResDto personalHearTimeResDto = personalStatisticsService.getHearTime(memberId);
        return new DataResponse<>("200", "타인의 가장 많이 듣는 시간대 통계 조회에 성공하였습니다.", personalHearTimeResDto);
    }

    @GetMapping("/tags/{memberId}")
    public DataResponse<PersonalTagsResDto> getYourPersonalTags(@RequestParam("memberId") long memberId) {
        PersonalTagsResDto personalTagsResDto = personalStatisticsService.getPersonalTags(memberId);
        return new DataResponse<>("200", "타인의 개인 태그 개수 조회에 성공하였습니다.", personalTagsResDto);
    }
}
