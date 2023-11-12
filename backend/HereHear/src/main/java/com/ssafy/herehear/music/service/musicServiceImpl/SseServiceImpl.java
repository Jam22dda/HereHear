package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.music.dto.response.SseResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.RegisteredMusicDslRepository;
import com.ssafy.herehear.music.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60 * 12;
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    private final RegisteredMusicDslRepository registeredMusicDslRepository;
    private final RegisterMusicMapper registerMusicMapper;


    @Override
    public SseEmitter subscribe(Long memberId, Object data) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitters.add(emitter);

        // Emitter가 완료될 때, 타임아웃 되었을 때 Emitter를 삭제한다.
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        // 회원 등록 후, 해당 사용자에게만 확인 응답을 보냄
        try {
            emitter.send(SseEmitter.event().id(String.valueOf(memberId)).name("subscribe").data(data));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }


    @Override
    public void notifyAllMembers(Object data) {
        log.info("[{}] 모든 사용자 전달 result: {}",ConstantsUtil.SSE_SCHEDULER, data);

        List<SseEmitter> deadEmitters = Collections.synchronizedList(new ArrayList<>());
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name("notification").data(data));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        emitters.removeAll(deadEmitters);
    }

    @Scheduled(cron = "0 * * * * ?")
    public void checkForDataChanges() {
        log.info("[{}] 실행", ConstantsUtil.SSE_SCHEDULER);
        List<SseResDto> sseResDtos = new ArrayList<>();

        List<SseResDto> deleteSseResDto = registeredMusicDslRepository.findByRegisterMusics(181,-180).stream()
                .map(registeredMusic -> registerMusicMapper.toSseResDto(0, registeredMusic))
                .toList();
        log.info("[{}] 삭제 result: {}", ConstantsUtil.SSE_SCHEDULER,deleteSseResDto);

        List<SseResDto> addSseResDto = registeredMusicDslRepository.findByRegisterMusics(-180,181).stream()
                .map(registeredMusic -> registerMusicMapper.toSseResDto(1, registeredMusic))
                .toList();
        log.info("[{}] 추가 result: {}", ConstantsUtil.SSE_SCHEDULER,addSseResDto);

        sseResDtos.addAll(deleteSseResDto);
        sseResDtos.addAll(addSseResDto);
        notifyAllMembers(sseResDtos);
    }
}
