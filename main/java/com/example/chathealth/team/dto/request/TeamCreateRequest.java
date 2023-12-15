package com.example.chathealth.team.dto.request;


import com.example.chathealth.team.domain.Team;
import com.example.chathealth.team.domain.User;

// DTO는 API 스펙이 중요 -> 조회 할때 쓸 필드와 getter 있어야함 -> 컨트롤러에서 매개변수로 넣어주자
public class TeamCreateRequest {
    private String TeamName;
    private Long Head;
    private Long UserId;
    private User user;
    private Team team;

    public Team getTeam(){
        return team;
    }
    public User getUser() {
        return user;
    }
    public String getTeamName() {
        return TeamName;
    }
    public Long getHead() {
        return Head;
    }

    public Long getUserId() {
        return UserId;
    }


}
