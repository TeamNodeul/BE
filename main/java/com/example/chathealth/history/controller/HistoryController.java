package com.example.chathealth.history.controller;


import com.example.chathealth.history.domain.CountHistory;
import com.example.chathealth.history.dto.CountHistoryResponse;
import com.example.chathealth.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {
    //  function: get count_sport from client and update DB, count_sport
    @Autowired
    private HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }
    public ResponseEntity<?> createCountHistory(@PathVariable Long userId, @RequestBody CountHistoryResponse countHistoryResponse) {
        try {
            CountHistory countHistory = convertToEntity(countHistoryResponse);
            historyService.saveCountHistory(countHistory); // 여기를 수정했습니다.
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    private CountHistory convertToEntity(CountHistoryResponse countHistoryResonse) {
        CountHistory countHistory = new CountHistory();


        countHistory.setSetCount(countHistoryResonse.getSetCount());
        countHistory.setSportCount(countHistoryResonse.getSportCount());
        countHistory.setWorkOutTime(countHistoryResonse.getWorkOutTime());

        // 변환된 엔티티를 반환
        return countHistory;
    }



}
