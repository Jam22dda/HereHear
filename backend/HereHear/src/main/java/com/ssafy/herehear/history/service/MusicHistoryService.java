package com.ssafy.herehear.history.service;

import com.ssafy.herehear.achievement.repository.MemberRepository;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.history.mapper.MusicHistoryMapper;
import com.ssafy.herehear.history.repository.MusicHistoryRepositoryImpl;
import com.ssafy.herehear.music.dto.response.LikeRegisteredMusicResDto;
import com.ssafy.herehear.history.repository.MusicHistoryRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepositoryImpl;
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
public class MusicHistoryService {

    private final MusicHistoryRepository musicHistoryRepository;
    private final MusicHistoryRepositoryImpl musicHistoryRepositoryImpl;

    private final RegisteredMusicService registeredMusicService;
    private final MusicHistoryMapper musicHistoryMapper;

    @Transactional
    public void registerPlayMusic(long memberId, long registeredMusicId) {
        log.info(registeredMusicService.logComment("최근 들은 음악 등록",memberId,registeredMusicId));

        findMusicHistory(memberId, registeredMusicId).ifPresentOrElse(
                existingMusicHistory -> {
                    existingMusicHistory.updateCreateTime(LocalDateTime.now());
                    musicHistoryRepository.save(existingMusicHistory);
                },
                () -> {
                    MusicHistory musicHistory = MusicHistory.builder()
                            .id(registeredMusicService.findMemberMusicId(memberId,registeredMusicId))
                            .member(registeredMusicService.findMember(memberId))
                            .registeredMusic(registeredMusicService.findByRegisterMusic(registeredMusicId))
                            .build();
                    musicHistoryRepository.save(musicHistory);
                }
        );

        log.info("registerPlayMusic success");
    }

    @Transactional
    public void deletePlayMusic(long memberId, long registeredMusicId){
        log.info(registeredMusicService.logComment("최근 들은 음악 삭제",memberId,registeredMusicId));

        registeredMusicService.findMember(memberId);

        findMusicHistory(memberId, registeredMusicId)
                .ifPresentOrElse(
                        musicHistoryRepository::delete,
                        () -> {
                            throw new CustomException(ExceptionStatus.NOT_FOUND_HISTORY_MUSIC);
                        }
                );
        log.info("deletePlayMusic success");
    }

    @Transactional
    public List<LikeRegisteredMusicResDto> getMusicHistoryList(long memberId){
        registeredMusicService.findMember(memberId);

        List<LikeRegisteredMusicResDto> likeRegisteredMusicResDtos = musicHistoryRepositoryImpl.findByMusicHistorys(memberId).stream()
                .map(findRegisteredMusic -> musicHistoryMapper.toLikeRegisteredMusicResDto(
                        findRegisteredMusic,
                        registeredMusicService.findRegisteredMusicLike(memberId, findRegisteredMusic.getRegisteredMusicId()))
                )
                .toList();
        log.info("getMusicHistoryList: "+ likeRegisteredMusicResDtos);

        return likeRegisteredMusicResDtos;
    }

    public Optional<MusicHistory> findMusicHistory(long memberId, long registeredMusicId){
        return musicHistoryRepository.findById(registeredMusicService.findMemberMusicId(memberId, registeredMusicId));
    }
}
