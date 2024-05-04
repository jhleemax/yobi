package com.yobi.yobiproject.member.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor //4개의 값을 다 넣은 생성자를 만들어줌
@NoArgsConstructor
public class MemberDTO {
    private String userId;
    private String userPass;
    private String username;
}
