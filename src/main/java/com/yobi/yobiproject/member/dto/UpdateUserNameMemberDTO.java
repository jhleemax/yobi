package com.yobi.yobiproject.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor //4개의 값을 다 넣은 생성자를 만들어줌
@NoArgsConstructor
public class UpdateUserNameMemberDTO {
    private String username;
}
