package com.yobi.yobiproject.member.service;

import com.yobi.yobiproject.exception.CustomErrorCode;
import com.yobi.yobiproject.exception.CustomException;
import com.yobi.yobiproject.member.Entity.Member;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.member.dto.LoginMemberDTO;
import com.yobi.yobiproject.member.dto.MemberDTO;
import com.yobi.yobiproject.member.dto.ResponseMemberDTO;
import com.yobi.yobiproject.member.dto.UpdateUserNameMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public HttpStatus save(MemberDTO memberDTO) {
        Member member = memberRepository.findByUserId(memberDTO.getUserId());
        if(member != null) {
            throw new CustomException(CustomErrorCode.USER_ALREADY_EXISTS);
        }
        else {
            if(memberDTO.getUserId().length() > 15) {
                throw new CustomException(CustomErrorCode.SIGNID_LONG_REQUEST);
            }
            else if(memberDTO.getUserPass().length() > 15) {
                throw new CustomException(CustomErrorCode.SIGNPW_LONG_REQUEST);
            }
            else if(memberDTO.getUsername().length() > 10) {
                throw new CustomException(CustomErrorCode.SIGNNAME_LONG_REQUEST);
            }
            else if(memberDTO.getUserId().length() < 6) {
                throw new CustomException(CustomErrorCode.SIGNID_SHORT_REQUEST);
            }
            else if(memberDTO.getUserPass().length() < 6) {
                throw new CustomException(CustomErrorCode.SIGNPW_SHORT_REQUEST);
            }
            else if(memberDTO.getUsername().length() < 2) {
                throw new CustomException(CustomErrorCode.SIGNNAME_SHORT_REQUEST);
            }
            else {
                Member newMember = Member.toMember(memberDTO);
                memberRepository.save(newMember);
                return HttpStatus.OK;
            }
        }
    }

    public void updateByName(UpdateUserNameMemberDTO updateUserNameMemberDTO, String userid) {
        Member member = memberRepository.findByUserId(userid);
        member.setUsername(updateUserNameMemberDTO.getUsername());
        memberRepository.save(member); //update의 기능을 다함
    }

    public ResponseMemberDTO getMemberById(String userid) {
        Member member = memberRepository.findByUserId(userid);
        return ResponseMemberDTO.toResponseMemberDTO(member);
    }

    public boolean check(LoginMemberDTO loginMemberDTO) {
        boolean result;
        Member member = memberRepository.findByUserId(loginMemberDTO.getUserId());
        if(member == null) {
            return false;
        }
        if(loginMemberDTO.getUserPass().equals(member.getUserPass())) {
            result = true;
        }
        else {
            result = false;
        }
        return result;
    }

    public void delete(String userid) {
        Member member = memberRepository.findByUserId(userid);
        memberRepository.delete(member);
    }
}
