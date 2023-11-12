package com.ssafy.herehear.history.service;

import com.ssafy.herehear.entity.MemberMusicId;
import com.ssafy.herehear.entity.MusicHistory;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.history.dto.response.PlayRegisteredMusicResDto;
import com.ssafy.herehear.history.mapper.MusicHistoryMapper;
import com.ssafy.herehear.history.repository.MusicHistoryDslRepository;
import com.ssafy.herehear.history.repository.MusicHistoryRepository;
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
public class MusicHistoryServiceImpl implements MusicHistoryService{

    private final MusicHistoryRepository musicHistoryRepository;
    private final MusicHistoryDslRepository musicHistoryDslRepository;

    private final MusicHistoryMapper musicHistoryMapper;

    @Override
    @Transactional
    public void registerPlayMusic(long memberId, long registeredMusicId) {
        log.info("[최근 들은 음악 등록] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);

        findMusicHistory(memberId, registeredMusicId).ifPresentOrElse(
                existingMusicHistory -> {
                    existingMusicHistory.updateCreateTime(LocalDateTime.now());
                    musicHistoryRepository.save(existingMusicHistory);
                },
                () -> {
                    MusicHistory musicHistory = MusicHistory.builder()
                            .id(findMemberMusicId(memberId,registeredMusicId))
                            .member(MemberUtil.findMember(memberId))
                            .registeredMusic(findByRegisterMusic(registeredMusicId))
                            .build();
                    musicHistoryRepository.save(musicHistory);
                }
        );

        log.info("registerPlayMusic success");
    }

    @Override
    @Transactional
    public void deletePlayMusic(long memberId, long registeredMusicId){
        log.info("[최근 들은 음악 삭제] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);

        findMusicHistory(memberId, registeredMusicId)
                .ifPresentOrElse(
                        musicHistoryRepository::delete,
                        () -> {
                            throw new CustomException(ExceptionStatus.NOT_FOUND_HISTORY_MUSIC);
                        }
                );
        log.info("deletePlayMusic success");
    }

    @Override
    @Transactional
    public List<PlayRegisteredMusicResDto> getMusicHistoryList(long memberId){
        log.info("[최근 들은 음악 조회] memberId: "+memberId);

        List<PlayRegisteredMusicResDto> playRegisteredMusicResDtos = musicHistoryDslRepository.findByMusicHistorys(memberId).stream()
                .map(findRegisteredMusic -> musicHistoryMapper.toPlayRegisteredMusicResDto(
                        findRegisteredMusic,
                        musicHistoryDslRepository.findByRegisteredMusicLike(
                                memberId,
                                findRegisteredMusic.getRegisteredMusicId()
                        ).isPresent())
                )
                .toList();
        log.info("getMusicHistoryList: "+ playRegisteredMusicResDtos);

        return playRegisteredMusicResDtos;
    }

    public Optional<MusicHistory> findMusicHistory(long memberId, long registeredMusicId){
        return musicHistoryRepository.findById(findMemberMusicId(memberId, registeredMusicId));
    }

    private RegisteredMusic findByRegisterMusic(long registeredMusicId){
        return musicHistoryDslRepository.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    private MemberMusicId findMemberMusicId(long memberId, long registeredMusicId){
        return MemberMusicId.builder().memberId(memberId).registeredMusicId(registeredMusicId).build();
    }
}