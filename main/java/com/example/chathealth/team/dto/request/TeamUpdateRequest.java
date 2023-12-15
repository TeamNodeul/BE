package com.example.chathealth.team.dto.request;

import com.example.chathealth.team.domain.Member;
import com.example.chathealth.team.domain.User;

public class TeamUpdateRequest {
    private String TeamName;
    private Long UserId;
    private User user;
    private Member member;
    public String getTeamName() {
        return TeamName;
    }
    public Long getUserId() {
        return UserId;
    }
    public User getUser() {
        return user;
    }
    public Member getMember() {
        return member;
    }


}
