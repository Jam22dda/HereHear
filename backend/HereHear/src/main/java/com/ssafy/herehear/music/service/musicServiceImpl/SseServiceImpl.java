package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.music.dto.response.SseResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.RegisteredMusicDslRepository;
import com.ssafy.herehear.music.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60 * 12;
    private final Map<Long, SseEmitter> emittersMap = new ConcurrentHashMap<>();
    private Map<Long, Integer> retryCountMap = new ConcurrentHashMap<>();

    private final RegisteredMusicDslRepository registeredMusicDslRepository;
    private final RegisterMusicMapper registerMusicMapper;


    @Override
    public SseEmitter subscribe(Long memberId, Object data) {
        SseEmitter existingEmitter = emittersMap.get(memberId);

        // 기존 사용자가 있으면 해당 Emitter를 끊도록 메시지를 보내고 새 Emitter 반환
        if (existingEmitter != null) {
            log.info("[기존 SSE 연결 해제] memberId: {}, mapSize: {}", memberId, emittersMap.size());
            existingEmitter.complete();
        }

        SseEmitter emitter = getSseEmitter(memberId);

        emittersMap.put(memberId, emitter);

        try {
            emitter.send(SseEmitter.event().id(String.valueOf(memberId)).name("sse").data(data));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    private SseEmitter getSseEmitter(Long memberId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);

        emitter.onCompletion(() -> {
            emittersMap.remove(memberId);
            emitter.complete();
            log.info("[{}] Completion mapSize: {}", ConstantsUtil.SSE_EVENT, emittersMap.size());
        });
        emitter.onTimeout(() -> {
            emittersMap.remove(memberId);
            log.info("[{}] Timeout mapSize: {}", ConstantsUtil.SSE_EVENT, emittersMap.size());
        });
        emitter.onError(e -> log.info("[{}] Error mapSize: {}", ConstantsUtil.SSE_EVENT, emittersMap.size()));
        return emitter;
    }

    @Override
    public void notifyAllMembers(Object data) {
        log.info("[{}] 모든 사용자 전달 result: {}", ConstantsUtil.SSE_SCHEDULER, data);

        synchronized(emittersMap) {
            Iterator<Map.Entry<Long, SseEmitter>> iterator = emittersMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, SseEmitter> entry = iterator.next();
                Long memberId = entry.getKey();
                SseEmitter emitter = entry.getValue();
                try {
                    synchronized(emitter) {
                        log.info("[{}] 전달 memberId: {}", ConstantsUtil.SSE_EVENT, memberId);
                        emitter.send(SseEmitter.event().name("sse").data(data));
                    }
                } catch (IOException e) {
                    log.info("[{}] 에러 memberId: {}", ConstantsUtil.SSE_EVENT, memberId);
                    int retryCount = retryCountMap.getOrDefault(memberId, 0);
                    if (retryCount < 5) {
                        retryCountMap.put(memberId, retryCount + 1);
                    } else {
                        emitter.completeWithError(e);
                        iterator.remove(); // 재시도 횟수 초과시 emitter 제거
                        retryCountMap.remove(memberId); // 재시도 카운트 맵에서 제거
                    }
                } catch (IllegalStateException ise) {
                    log.info("[{}] 완료되었거나 취소된 emitter memberId: {}", ConstantsUtil.SSE_EVENT, memberId);
                    iterator.remove(); // 이미 완료되었거나 취소된 emitter 제거
                    retryCountMap.remove(memberId); // 재시도 카운트 맵에서 제거
                }
            }
        }
    }

    @Scheduled(cron = "0 * * * * ?")
    public void checkForDataChanges() {
        log.info("[{}] 실행", ConstantsUtil.SSE_SCHEDULER);
        List<SseResDto> sseResDtos = new ArrayList<>();

        List<SseResDto> deleteSseResDto = registeredMusicDslRepository.findByRegisterMusics(181, -180).stream()
                .map(registeredMusic -> registerMusicMapper.toSseResDto(0, registeredMusic))
                .toList();
        log.info("[{}] 삭제 result: {}", ConstantsUtil.SSE_SCHEDULER, deleteSseResDto);

        List<SseResDto> addSseResDto = registeredMusicDslRepository.findByRegisterMusics(-179, 180).stream()
                .map(registeredMusic -> registerMusicMapper.toSseResDto(1, registeredMusic))
                .toList();
        log.info("[{}] 추가 result: {}", ConstantsUtil.SSE_SCHEDULER, addSseResDto);

        sseResDtos.addAll(deleteSseResDto);
        sseResDtos.addAll(addSseResDto);
        notifyAllMembers(sseResDtos);
    }

}
