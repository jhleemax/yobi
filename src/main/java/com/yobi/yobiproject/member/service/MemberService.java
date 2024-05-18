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

    public HttpStatus save(MemberDTO memberDTO) { // 회원가입
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

    public HttpStatus updateByName(UpdateUserNameMemberDTO updateUserNameMemberDTO, String userid) { // 닉변
        Member member = memberRepository.findByUserId(userid);
        if(member == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        else {
            if(updateUserNameMemberDTO.getUsername().length() < 2) {
                throw new CustomException(CustomErrorCode.SIGNNAME_SHORT_REQUEST);
            }
            else if(updateUserNameMemberDTO.getUsername().length() > 10) {
                throw new CustomException(CustomErrorCode.SIGNNAME_LONG_REQUEST);
            }
            else {
                member.setUsername(updateUserNameMemberDTO.getUsername());
                memberRepository.save(member); //update의 기능을 다함
                return HttpStatus.OK;
            }
        }
    }

    public ResponseMemberDTO getMemberById(String userid) { // 닉네임 및 아이디 조회
        Member member = memberRepository.findByUserId(userid);
        return ResponseMemberDTO.toResponseMemberDTO(member);
    }

    public HttpStatus login(LoginMemberDTO loginMemberDTO) { // 로그인
        Member member = memberRepository.findByUserIdAndUserPass(loginMemberDTO.getUserId(),
                loginMemberDTO.getUserPass());
        if(member == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        else {
            return HttpStatus.OK;
        }
    }

    public HttpStatus delete(LoginMemberDTO loginMemberDTO) { // 회원탈퇴
        Member member = memberRepository.findByUserIdAndUserPass(loginMemberDTO.getUserId(),
                loginMemberDTO.getUserPass());
        if(member == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        else {
            memberRepository.delete(member);
            return HttpStatus.OK;
        }
    }
}
