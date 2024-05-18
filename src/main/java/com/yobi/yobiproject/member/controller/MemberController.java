package com.yobi.yobiproject.member.controller;

import com.yobi.yobiproject.member.Entity.Member;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.member.dto.LoginMemberDTO;
import com.yobi.yobiproject.member.dto.MemberDTO;
import com.yobi.yobiproject.member.dto.ResponseMemberDTO;
import com.yobi.yobiproject.member.dto.UpdateUserNameMemberDTO;
import com.yobi.yobiproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping(value = "/user/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody MemberDTO memberDTO){ // 회원가입
        return ResponseEntity.status(memberService.save(memberDTO)).build();
    }
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId){ // 사용자 아이디, 닉네임 조회
        ResponseMemberDTO member = memberService.getMemberById(userId);
        return ResponseEntity.ok().body(member);
    }
    @PatchMapping(value = "/user/update/{userId}")  // 닉네임 변경
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserNameMemberDTO updateUserNameMemberDTO){
        return ResponseEntity.status(memberService.updateByName(updateUserNameMemberDTO, userId)).build();
    }

    @PostMapping(value = "/user/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginMemberDTO loginMemberDTO) { // 로그인
        return ResponseEntity.status(memberService.login(loginMemberDTO)).body("로그인 성공");
    }

    @PostMapping(value ="/user/delete")
    public ResponseEntity<?> deleteUser(@RequestBody LoginMemberDTO loginMemberDTO) { // 회원탈퇴(DTO 재사용좀 할게요 ㅎ..)
        return ResponseEntity.status(memberService.delete(loginMemberDTO)).body("회원탈퇴 성공");
    }
}
