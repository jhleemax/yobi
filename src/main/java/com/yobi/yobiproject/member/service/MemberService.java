package com.yobi.yobiproject.member.service;

import com.yobi.yobiproject.member.Entity.Member;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.member.dto.MemberDTO;
import com.yobi.yobiproject.member.dto.ResponseMemberDTO;
import com.yobi.yobiproject.member.dto.UpdateUserNameMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        Member member = Member.toMember(memberDTO);
        memberRepository.save(member); //save, update의 기능을 다함
    }

    public void update(UpdateUserNameMemberDTO updateUserNameMemberDTO, String userid) {
        Member member = memberRepository.findByUserId(userid);
        member.setUsername(updateUserNameMemberDTO.getUsername());
        memberRepository.save(member); //update의 기능을 다함
    }

    public ResponseMemberDTO getMemberById(String userid) {
        Member member = memberRepository.findByUserId(userid);
        return ResponseMemberDTO.toResponseMemberDTO(member);
    }
}
