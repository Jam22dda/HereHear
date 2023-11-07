package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.music.dto.response.SseResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.musicRepositoryImpl.RegisteredMusicRepositoryImpl;
import com.ssafy.herehear.music.repository.musicRepositoryImpl.SseRepositoryImpl;
import com.ssafy.herehear.music.service.SseService;
import com.ssafy.herehear.music.util.HourFilterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60 * 12;// timeout 12시간

    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;
    private final SseRepositoryImpl sseRepositoryImpl;
    private final RegisterMusicMapper registerMusicMapper;

    @Override
    public SseEmitter subscribe(Long memberId) {
        sendToClient(memberId, "SseEmitter 구독 memberId: "+memberId);

        return createEmitter(memberId);
    }

    @Override
    public void notify(Long memberId, Object event) {
        sendToClient(memberId, event);
    }

    @Override
    public void sendToClient(Long memberId, Object data) {
        SseEmitter emitter = sseRepositoryImpl.get(memberId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().id(String.valueOf(memberId)).name("sse").data(data));
            } catch (IOException exception) {
                sseRepositoryImpl.deleteById(memberId);
                emitter.completeWithError(exception);
            }
        }
    }

    @Override
    public void sendAllClient(Object data) {
        sseRepositoryImpl.forEach((id, emitter) -> {
            try {
                emitter.send(SseEmitter.event().id(String.valueOf(id)).name("sse").data(data));
            } catch (IOException exception) {
                log.info("sendAllClient exception: "+exception);
            }
        });
    }

    @Override
    public SseEmitter createEmitter(Long memberId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        sseRepositoryImpl.save(memberId, emitter);

        // Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
        emitter.onCompletion(() -> sseRepositoryImpl.deleteById(memberId));
        // Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
        emitter.onTimeout(() -> sseRepositoryImpl.deleteById(memberId));

        return emitter;
    }

    @Scheduled(cron = "0 0 * * * ?")//정각 마다 실행
//    @Scheduled(fixedRate = 5000)//test
    public void checkForDataChanges() {
        List<SseResDto> sseResDtos = new ArrayList<>();

        //SSE 삭제
        List<SseResDto> deleteSseResDto = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .filter(HourFilterUtils::beforeHourFilter)
                .map(registeredMusic -> registerMusicMapper.toSseResDto(0, registeredMusic))
                .toList();

        //SSE 추가
        List<SseResDto> addSseResDto = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .filter(HourFilterUtils::afterHourFilter)
                .map(registeredMusic -> registerMusicMapper.toSseResDto(1, registeredMusic))
                .toList();


        sseResDtos.addAll(deleteSseResDto);
        sseResDtos.addAll(addSseResDto);
        log.info("checkForDataChanges sseResDtos: "+ sseResDtos);

        sendAllClient(sseResDtos);
    }

}