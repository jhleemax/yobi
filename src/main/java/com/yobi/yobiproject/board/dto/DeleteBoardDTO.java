package com.yobi.yobiproject.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteBoardDTO {
    private int boardNum;
    private String userId;
}
