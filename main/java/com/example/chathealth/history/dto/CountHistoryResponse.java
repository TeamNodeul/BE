package com.example.chathealth.history.dto;

import java.sql.Time;

public class CountHistoryResponse {
    private Integer setCount;
    private Integer sportCount;
    private Time workOutTime;

    public Integer getSetCount() {
        return setCount;
    }

    public Integer getSportCount() {
        return sportCount;
    }

    public Time getWorkOutTime() {
        return workOutTime;
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
