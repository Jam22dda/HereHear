package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.response.SseResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.musicRepositoryImpl.RegisteredMusicRepositoryImpl;
import com.ssafy.herehear.music.repository.musicRepositoryImpl.SseRepositoryImpl;
import com.ssafy.herehear.music.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60 * 12;// timeout 12시간
    private final LocalTime currentTime = LocalDateTime.now().toLocalTime();// 현재 시간

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("sendToClient data: "+data);
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

    //처음에는 실행하지 않고 1시간 후부터 매 1시간마다 실행
    @Scheduled(fixedRate =  3600000, initialDelay =  3600000)
    public void checkForDataChanges() {
        List<SseResDto> sseResDtos = new ArrayList<>();

        //SSE 추가
        List<RegisteredMusic> addRegisteredMusics = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .filter(this::afterHourFilter)
                .toList();

        List<SseResDto> addSseResDto = addRegisteredMusics.stream()
                .map(registeredMusic -> registerMusicMapper.toSseResDto(1, registeredMusic))
                .toList();
        log.info("checkForDataChanges addSseResDto: "+ addSseResDto);

        //SSE 삭제
        List<RegisteredMusic> deleteRegisteredMusics = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .filter(this::beforeHourFilter)
                .toList();

        List<SseResDto> deleteSseResDto = deleteRegisteredMusics.stream()
                .map(registeredMusic -> registerMusicMapper.toSseResDto(0, registeredMusic))
                .toList();
        log.info("checkForDataChanges deleteSseResDto: "+ deleteSseResDto);

        sseResDtos.addAll(addSseResDto);
        sseResDtos.addAll(deleteSseResDto);

        sendAllClient(sseResDtos);
    }

    public boolean beforeHourFilter(RegisteredMusic findRegisteredMusic){
        // 현재 시간으로부터 -1h 이내인 데이터만 필터링
        long hoursDifference = ChronoUnit.HOURS.between(currentTime, findRegisteredMusic.getCreateTime());
        return hoursDifference >= -1 && hoursDifference <= 0 || hoursDifference >= 23;
    }

    public boolean afterHourFilter(RegisteredMusic findRegisteredMusic){
        // 현재 시간으로부터 -1h 이내인 데이터만 필터링
        long hoursDifference = ChronoUnit.HOURS.between(currentTime, findRegisteredMusic.getCreateTime());
        return hoursDifference >= 0 && hoursDifference <= 1 || hoursDifference >= 23;
    }
}