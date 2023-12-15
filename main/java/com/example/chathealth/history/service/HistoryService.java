package com.example.chathealth.history.service;

import com.example.chathealth.history.domain.CountHistory;
import com.example.chathealth.history.domain.CountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    @Autowired
    private final CountHistoryRepository countHistoryRepository;

    public HistoryService(CountHistoryRepository countHistoryRepository) {
        this.countHistoryRepository = countHistoryRepository;
    }
    public CountHistory saveCountHistory(CountHistory countHistory) {
        return countHistoryRepository.save(countHistory);
    }

}
