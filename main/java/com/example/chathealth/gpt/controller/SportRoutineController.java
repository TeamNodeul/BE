package com.example.chathealth.gpt.controller;

import com.example.chathealth.gpt.domain.*;
import com.example.chathealth.gpt.dto.request.SportRoutineCreateRequest;
import com.example.chathealth.gpt.service.GptSportRoutineService;
import com.example.chathealth.gpt.service.SportRoutineService;
import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sportRoutine")
public class SportRoutineController {

    @Value("${openai.api.key}")
    private String apiKey;
    private final SportRoutineService sportRoutineService;
    private final GptSportRoutineService gptSportRoutineService;
    private final UserRepository userRepository;
    private final SportRoutineRepository sportRoutineRepository;
    private final SportRoutineListRepository sportRoutineListRepository;
    @Autowired
    public SportRoutineController(
            GptSportRoutineService gptSportRoutineService,
            SportRoutineService sportRoutineService ,
            UserRepository userRepository,
            SportRoutineRepository sportRoutineRepository,
            SportRoutineListRepository sportRoutineListRepository) {
        this.gptSportRoutineService = gptSportRoutineService;
        this.sportRoutineService = sportRoutineService;
        this.userRepository = userRepository;
        this.sportRoutineRepository = sportRoutineRepository;
        this.sportRoutineListRepository = sportRoutineListRepository;
    }


    // 운동 루틴 생성 -사용자
    @PostMapping("/api/create/{userId}")
    public ResponseEntity<SportRoutine> createSportRoutine(@PathVariable Long userId,
                                                           @RequestBody SportRoutineCreateRequest request) {
        SportRoutine sportRoutine = sportRoutineService.createSportRoutine(userId, request);
        return new ResponseEntity<>(sportRoutine, HttpStatus.CREATED);
    }


    //운동 루틴 생성 - gpt
    @PostMapping("/api/user/{userId}/gpt/sportRoutine")
    public ResponseEntity<String> callGptForUser(@PathVariable Long userId, @RequestBody String[] additionalInfo) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

            String fixedInput = createFixedInput(user,additionalInfo); // 고정 입력값 생성 - 클라이언트 정보 포함
            String sportRoutineContent = gptSportRoutineService.getGptResponseAndSave(apiKey, fixedInput, user);
            // save the response from GPT to dietContent that typecasted to String

            return ResponseEntity.ok(sportRoutineContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()); // 500 서버 내부 오류
        }
    }

    private String createFixedInput(User user, String[] additionalInfo) { //유저 정보랑 클라이언트 정보
        // 기본 fixedInput 문자열 설정
        String baseInput = String.format(
                "운동 식단을 추천해줘. 하루 3끼 기준으로 일주일 치 요일 별로, 각 끼니마다 메뉴 한가지만 지정해줘. JSON형식으로 반환해줘. JSON 형식은 { \"월\": { \"아침\":{ \"메뉴\" : \"\", \"분량\" : \"\"}, \"점심\":{ \"메뉴\" : \"\", \"분량\" : \"\"}, \"저녁\": { \"메뉴\" : \"\", \"분량\" : \"\"} }, ... }. 사용자의 키는 %dcm이고, 몸무게는 %dkg입니다.",
                user.getHeight(), user.getWeight());

        // 추가 정보를 fixedInput에 통합
        StringBuilder fixedInputBuilder = new StringBuilder(baseInput);
        for (String info : additionalInfo) {
            fixedInputBuilder.append(" ").append(info);
        }

        // 완성된 fixedInput 반환
        return fixedInputBuilder.toString();
    }

    // 운동 루틴 조회 API
    @GetMapping("/api/sport/{userId}/routines")
    public ResponseEntity<List<SportRoutine>> getUserDiets(@PathVariable Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));


            List<SportRoutine> routines = sportRoutineRepository
                    .findAllByUser(user).stream().collect(Collectors.toList());
            return ResponseEntity.ok(routines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/api/sport/{userId}/routineList/{SportRoutineId}")
    public ResponseEntity<List<SportRoutineList>> getRoutine(@PathVariable Long userId, @PathVariable Long SportRoutineId){
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
            SportRoutine sportRoutine = sportRoutineRepository.findById(SportRoutineId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid sportRoutine ID"));

            List<SportRoutineList> routineList = sportRoutineListRepository.findAllByUserAndSportRoutine(user, sportRoutine).stream().collect(Collectors.toList());
            return ResponseEntity.ok(routineList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }










    //


    //








}
