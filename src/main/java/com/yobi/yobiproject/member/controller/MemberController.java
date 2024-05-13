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
    public ResponseEntity<?> signup(@RequestBody MemberDTO memberDTO){
        memberService.save(memberDTO);
        return ResponseEntity.created(URI.create("/user/" + memberDTO.getUserId())).build();
    }
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId){
        ResponseMemberDTO member = memberService.getMemberById(userId);
        return ResponseEntity.ok().body(member);
    }
    @PatchMapping(value = "/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserNameMemberDTO updateUserNameMemberDTO){
        memberService.update(updateUserNameMemberDTO, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/user/login")
    public boolean loginUser(@RequestBody LoginMemberDTO loginMemberDTO) {
        if(loginMemberDTO.getUserId() == null || loginMemberDTO.getUserPass() == null) {
            return false;
        }
        boolean result = memberService.check(loginMemberDTO);
        if(result) {
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping(value = "/user/delete/{userId}")
    public ResponseEntity<?> DeleteUser(@PathVariable("userId") String userId) { // 보안 검사 미구현
        memberService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
