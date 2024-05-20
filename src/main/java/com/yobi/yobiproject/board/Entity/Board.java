package com.yobi.yobiproject.board.Entity;

import com.yobi.yobiproject.board.dto.BoardDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="board")
public class Board {
    @Id
    @Column(name = "board_num")
    private int boardNum;

    @Column(name = "userid")
    private String userId;

    @Column(name = "board_date")
    private LocalDateTime boardDate;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_category")
    private String boardCategory;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_image")
    private String boardImage;

    @Column(name = "board_nice")
    private int boardNice;

    @Column(name = "board_report")
    private int boardReport;

    @Builder
    public static Board toBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContent(boardDTO.getBoardContent());
        board.setBoardCategory(boardDTO.getBoardCategory());
        board.setUserId(boardDTO.getUserId());
        board.setBoardImage(boardDTO.getBoardImage());
        return board;
    }
}
