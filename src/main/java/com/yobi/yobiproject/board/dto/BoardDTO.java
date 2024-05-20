package com.yobi.yobiproject.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDTO {
    private String userId;
    private String boardContent;
    private String boardCategory;
    private String boardTitle;
    private String boardImage;
}
