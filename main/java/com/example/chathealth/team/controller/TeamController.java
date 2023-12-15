package com.example.chathealth.team.controller;

import com.example.chathealth.team.domain.Member;
import com.example.chathealth.team.domain.Team;
import com.example.chathealth.team.dto.request.TeamCreateRequest;
import com.example.chathealth.team.dto.request.TeamUpdateRequest;
import com.example.chathealth.team.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    // 생성자 주입 (생략 가능)
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // 팀 생성
    @PostMapping
    public void createTeam(@RequestBody TeamCreateRequest request){
        teamService.createTeam(request);

    }

    // 현재 사용자가 속한 모든 팀 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Team>> getTeamsByUserId(@PathVariable Long userId) {
        List<Team> teams = teamService.getTeamsByUserId(userId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // 팀 멤버 조회
    @GetMapping("/{teamId}/members")
    public ResponseEntity<List<Member>> getTeamMembers(@PathVariable Long teamId) {
        List<Member> members = teamService.getTeamMembers(teamId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // 팀 업데이트
    @PutMapping("/{teamId}")
    public void updateTeam(@PathVariable Long teamId, @RequestBody TeamUpdateRequest request) {
       teamService.updateTeam(teamId, request);

    }

    // 팀 삭제
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}