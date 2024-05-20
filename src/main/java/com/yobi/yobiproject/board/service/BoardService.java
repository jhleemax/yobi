package com.yobi.yobiproject.board.service;

import com.yobi.yobiproject.board.Entity.Board;
import com.yobi.yobiproject.board.Entity.BoardRepository;
import com.yobi.yobiproject.board.dto.BoardDTO;
import com.yobi.yobiproject.exception.CustomErrorCode;
import com.yobi.yobiproject.exception.CustomException;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    public HttpStatus save(BoardDTO boardDTO) {
        if(memberRepository.findByUserId(boardDTO.getUserId()) == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        else {
            if(boardDTO.getBoardTitle().isEmpty()) {
                throw new CustomException(CustomErrorCode.BOARD_NOT_TITLE);
            }
            else if(boardDTO.getBoardTitle().length() > 30) {
                throw new CustomException(CustomErrorCode.BOARD_LONG_TITLE);
            }
            else if(boardDTO.getBoardContent().isEmpty()) {
                throw new CustomException(CustomErrorCode.BOARD_NOT_CONTENT);
            }
            else if(boardDTO.getBoardContent().length() > 1000) {
                throw new CustomException(CustomErrorCode.BOARD_LONG_CONTENT);
            }
            else if(boardDTO.getBoardCategory().isEmpty()) {
                throw new CustomException(CustomErrorCode.BOARD_NOT_CATEGORY);
            }
            else {
                Board board = Board.toBoard(boardDTO);
                boardRepository.save(board);
                return HttpStatus.OK;
            }
        }
    }

    public List<Board> list() { // 전체 반환
        if(boardRepository.findAll().isEmpty()) {
            throw new CustomException(CustomErrorCode.BOARD_NOT_FOUND);
        }
        else {
            return boardRepository.findAll();
        }
    }

    public Board read(int boardNum) { // 상세보기
    }
}
