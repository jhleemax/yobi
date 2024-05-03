package com.yobi.yobiproject.member.dto;

import com.yobi.yobiproject.member.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMemberDTO {
    private String userId;
    private String username;

    public static ResponseMemberDTO toResponseMemberDTO(Member member){
        return new ResponseMemberDTO(member.getUserId(), member.getUsername());
    }
}
