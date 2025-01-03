package com.example.chathealth.team.controller;


import com.example.chathealth.team.domain.Member;
import com.example.chathealth.team.dto.response.MemberResponse;
import com.example.chathealth.team.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Member CRUD API

    //Create
    // 멤버 추가
//    @PostMapping
//    public ResponseEntity<Member> addMember(@RequestBody MemberResponse memberResponse) {
//        Member member = memberService.addMember(memberResponse);
//        return new ResponseEntity<>(member, HttpStatus.CREATED);
//    }


    //Read - 멤버 찾기
//    @GetMapping("/{memberId}")
//    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
//        Member member = memberService.getMember(memberId);
//        return new ResponseEntity<>(member, HttpStatus.OK);
//    }

    //Delete
    // 멤버 삭제 - 그룹 탈퇴 시 (프론트에서 요청시 마무리)
//    @DeleteMapping("/{memberId}")
//    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
//        memberService.deleteMember(memberId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }






}
