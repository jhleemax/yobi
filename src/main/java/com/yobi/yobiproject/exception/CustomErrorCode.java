package com.yobi.yobiproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-001", "사용자를 찾을 수 없습니다."),
    RECIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-002", "레시피를 찾을 수 없습니다."),
    USER_NOT_IDEQUAL(HttpStatus.BAD_REQUEST, "ACCOUNT-003", "유저 ID가 일치하지 않습니다."),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "ACCOUNT-004", "이미 존재하는 ID입니다."),
    SIGNID_LONG_REQUEST(HttpStatus.BAD_REQUEST, "ACCOUNT-005", "아이디를 15글자 이내로 설정해주세요."),
    SIGNPW_LONG_REQUEST(HttpStatus.BAD_REQUEST, "ACCOUNT-006", "비밀번호를 15글자 이내로 설정해주세요."),
    SIGNNAME_LONG_REQUEST(HttpStatus.BAD_REQUEST, "ACCOUNT-007", "닉네임을 10글자 이내로 설정해주세요."),
    SIGNID_SHORT_REQUEST(HttpStatus.BAD_REQUEST, "ACCOUNT-008", "아이디를 6글자 이상 적어주세요."),
    SIGNPW_SHORT_REQUEST(HttpStatus.BAD_REQUEST, "ACCOUNT-009", "비밀번호를 6글자 이상 적어주세요."),
    SIGNNAME_SHORT_REQUEST(HttpStatus.BAD_REQUEST, "ACCOUNT-010", "닉네임을 2글자 이상 적어주세요.");

    private final HttpStatus httpStatus;	// HttpStatus
    private final String code;				// ACCOUNT-001
    private final String message;			// 설명
}
