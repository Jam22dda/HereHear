package com.ssafy.herehear.history.service;

import com.ssafy.herehear.entity.MemberMusicId;
import com.ssafy.herehear.entity.MusicHistory;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.ConstantsUtil;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicHistoryServiceImpl implements MusicHistoryService {

    private final MusicHistoryRepository musicHistoryRepository;
    private final MusicHistoryDslRepository musicHistoryDslRepository;

    private final MusicHistoryMapper musicHistoryMapper;

    @Override
    @Transactional
    public void registerPlayMusic(long memberId, long registeredMusicId) {
        log.info("[{}] memberId: {}, registeredMusicId: {}", ConstantsUtil.HISTORY_REGISTER_MUSIC, memberId, registeredMusicId);

        musicHistoryRepository.findById(findMemberMusicId(memberId, registeredMusicId))
                .ifPresentOrElse(
                        existingMusicHistory -> {
                            log.info("[{}] 업데이트", ConstantsUtil.HISTORY_REGISTER_MUSIC);

                            existingMusicHistory.updateCreateTime(LocalDateTime.now());
                            musicHistoryRepository.save(existingMusicHistory);
                        },
                        () -> {
                            log.info("[{}] 저장", ConstantsUtil.HISTORY_REGISTER_MUSIC);

                            MusicHistory musicHistory = MusicHistory.builder()
                                    .id(findMemberMusicId(memberId, registeredMusicId))
                                    .member(MemberUtil.findMember(memberId))
                                    .registeredMusic(findByRegisterMusic(registeredMusicId))
                                    .build();
                            musicHistoryRepository.save(musicHistory);
                        }
                );
        log.info("[{}] 성공", ConstantsUtil.HISTORY_REGISTER_MUSIC);
    }

    @Override
    @Transactional
    public void deletePlayMusic(long memberId, long registeredMusicId) {
        log.info("[{}] memberId: {}, registeredMusicId: {}", ConstantsUtil.HISTORY_DELETE_MUSIC, memberId, registeredMusicId);

        musicHistoryRepository.findById(findMemberMusicId(memberId, registeredMusicId))
                .ifPresentOrElse(
                        musicHistoryRepository::delete,
                        () -> {
                            throw new CustomException(ExceptionStatus.NOT_FOUND_HISTORY_MUSIC);
                        }
                );
        log.info("[{}] 성공", ConstantsUtil.HISTORY_DELETE_MUSIC);
    }

    @Override
    @Transactional
    public List<PlayRegisteredMusicResDto> getMusicHistoryList(long memberId) {
        log.info("[{}] memberId: {}", ConstantsUtil.HISTORY_MUSIC_LIST, memberId);

        List<PlayRegisteredMusicResDto> result = musicHistoryDslRepository.findByMusicHistorys(memberId).stream()
                .map(registeredMusic -> musicHistoryMapper.toPlayRegisteredMusicResDto(registeredMusic, true))
                .toList();
        log.info("[{}] 성공 result: {}", ConstantsUtil.HISTORY_MUSIC_LIST, result);

        return result;
    }

    private RegisteredMusic findByRegisterMusic(long registeredMusicId) {
        return musicHistoryDslRepository.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    private MemberMusicId findMemberMusicId(long memberId, long registeredMusicId) {
        return MemberMusicId.builder().memberId(memberId).registeredMusicId(registeredMusicId).build();
    }
}