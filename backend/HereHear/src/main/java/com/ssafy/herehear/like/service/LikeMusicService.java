package com.ssafy.herehear.like.service;

import com.ssafy.herehear.achievement.repository.MemberRepository;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.like.repository.LikeMusicRepository;
import com.ssafy.herehear.music.dto.response.LikeRegisteredMusicResDto;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeMusicService {

    private final LikeMusicRepository likeMusicRepository;
    private final RegisteredMusicService registeredMusicService;

    public void registerlikeMusic(Long memberId, Long registeredMusicId){
        log.info(registeredMusicService.logComment("좋아요 등록",memberId,registeredMusicId));

        findLikeMusic(memberId, registeredMusicId).ifPresentOrElse(
                existingLikeMusic -> {
                    existingLikeMusic.updateCreateTime(LocalDateTime.now());
                    likeMusicRepository.save(existingLikeMusic);
                },
                () -> {
                    LikeMusic likeMusic = LikeMusic.builder()
                            .id(registeredMusicService.findMemberMusicId(memberId,registeredMusicId))
                            .member(registeredMusicService.findMember(memberId))
                            .registeredMusic(registeredMusicService.findByRegisterMusic(registeredMusicId))
                            .build();
                    likeMusicRepository.save(likeMusic);
                }
        );
        log.info("registerLike success");
    }

    @Transactional
    public void deletelikeMusic(long memberId, long registeredMusicId){
        log.info(registeredMusicService.logComment("좋아요 취소",memberId,registeredMusicId));
        registeredMusicService.findMember(memberId);

        findLikeMusic(memberId, registeredMusicId)
                .ifPresentOrElse(
                        likeMusicRepository::delete,
                        () -> {
                            throw new CustomException(ExceptionStatus.NOT_FOUND_LIKE_MUSIC);
                        }
                );
        log.info("deletelikeMusic success");
    }
//
//    @Transactional
//    public List<LikeRegisteredMusicResDto> likeMusicList(long memberId){
//        registeredMusicService.findMember(memberId);
//
//        List<LikeRegisteredMusicResDto> likeRegisteredMusicResDtos = musicHistoryRepositoryImpl.findByMusicHistorys(memberId).stream()
//                .map(findRegisteredMusic -> musicHistoryMapper.toLikeRegisteredMusicResDto(
//                        findRegisteredMusic,
//                        registeredMusicService.findRegisteredMusicLike(memberId, findRegisteredMusic.getRegisteredMusicId()))
//                )
//                .toList();
//        log.info("getMusicHistoryList: "+ likeRegisteredMusicResDtos);
//
//        return likeRegisteredMusicResDtos;
//    }

    public Optional<LikeMusic> findLikeMusic(long memberId, long registeredMusicId){
        return likeMusicRepository.findById(registeredMusicService.findMemberMusicId(memberId, registeredMusicId));
    }

}
