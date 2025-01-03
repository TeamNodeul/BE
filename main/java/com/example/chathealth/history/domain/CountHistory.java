package com.example.chathealth.history.domain;
import com.example.chathealth.gpt.domain.SportCategory;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "counthistory")
public class CountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;


    @ManyToOne
    private SportCategory sportCategory;


    @ManyToOne
    @JoinColumn(nullable = false)
    private DailyHistory dailyHistory;

    @Column(nullable = true, name = "set_count")
    private Integer setCount;


    @Column(nullable = true, name = "sport_count")
    private Integer sportCount;

    @Column(nullable = true, name = "volume")
    private Integer volume;
    @Column(nullable = false, name = "work_time")
    private Time workOutTime;

    public CountHistory() {
    }
    public CountHistory(SportCategory sportCategory, DailyHistory dailyHistory, Integer setCount, Integer sportCount, Time workOutTime) {
        this.sportCategory = sportCategory;
        this.dailyHistory = dailyHistory;
        this.setCount = setCount;
        this.sportCount = sportCount;
        this.workOutTime = workOutTime;
    }

    public CountHistory(Integer setCount, Integer sportCount, Integer volume, Time workOutTime) {
        this.setCount = setCount;
        this.sportCount = sportCount;
        this.volume = volume;
        this.workOutTime = workOutTime;
    }

    public CountHistory(Integer setCount, Integer sportCount, Time workOutTime) {
        this.setCount = setCount;
        this.sportCount = sportCount;
        this.workOutTime = workOutTime;
    }

    public Long getId() {
        return id;
    }

    public SportCategory getSportCategory() {
        return sportCategory;
    }

    public DailyHistory getDailyHistory() {
        return dailyHistory;
    }

    public Integer getSetCount() {
        return setCount;
    }

    public Integer getSportCount() {
        return sportCount;
    }

    public Time getWorkOutTime() {
        return workOutTime;
    }

    public Integer getVolume() {return volume;}

    public void setVolume(Integer volume) {this.volume = volume;}
    public void setSportCategory(SportCategory sportCategory) {
        this.sportCategory = sportCategory;
    }

    public void setDailyHistory(DailyHistory dailyHistory) {
        this.dailyHistory = dailyHistory;
    }

    public void setSetCount(Integer setCount) {
        this.setCount = setCount;
    }

    public void setSportCount(Integer sportCount) {
        this.sportCount = sportCount;
    }

    public void setWorkOutTime(Time workOutTime) {
        this.workOutTime = workOutTime;
    }
}
