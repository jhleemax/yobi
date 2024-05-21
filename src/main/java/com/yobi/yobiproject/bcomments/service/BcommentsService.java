package com.yobi.yobiproject.bcomments.service;

import com.yobi.yobiproject.bcomments.Entity.Bcomments;
import com.yobi.yobiproject.bcomments.Entity.BcommentsRepository;
import com.yobi.yobiproject.bcomments.dto.BcommentsDTO;
import com.yobi.yobiproject.bcomments.dto.DeleteBcommentsDTO;
import com.yobi.yobiproject.bcomments.dto.UpdateBcommentsDTO;
import com.yobi.yobiproject.board.Entity.BoardRepository;
import com.yobi.yobiproject.exception.CustomErrorCode;
import com.yobi.yobiproject.exception.CustomException;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.rcomments.Entity.Rcomments;
import com.yobi.yobiproject.rcomments.dto.DeleteRcommentsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BcommentsService {
    private final BcommentsRepository bcommentsRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public HttpStatus save(BcommentsDTO bcommentsDTO) { // 댓글 작성

        if(boardRepository.findByBoardNum(bcommentsDTO.getBoardNum()) == null) { // 게시판 존재유무 확인
            throw new CustomException(CustomErrorCode.BOARD_NOT_FOUND);
        }
        else {
            if(bcommentsDTO.getBcommentContent().length() > 100) {
                throw new CustomException(CustomErrorCode.COMMENT_LONG_REQUEST);
            }
            else if(bcommentsDTO.getBcommentContent().isEmpty()) {
                throw new CustomException(CustomErrorCode.COMMENT_NOT_CONTENT);
            }
            else {
                Bcomments bcomments = Bcomments.toBcomments(bcommentsDTO);
                bcommentsRepository.save(bcomments);
                return HttpStatus.OK;
            }
        }
    }

    public List<Bcomments> read(int boardNum) {
        List<Bcomments> bcomments = bcommentsRepository.findByBoardNum(boardNum);
        if(boardRepository.findByBoardNum(boardNum) == null) {
            throw new CustomException(CustomErrorCode.BOARD_NOT_FOUND);
        }
        else {
            return bcomments;
        }
    }

    public HttpStatus update(int bcommentNum, UpdateBcommentsDTO updateBcommentsDTO) {
        if(memberRepository.findByUserId(updateBcommentsDTO.getUserId()) == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        else {
            if(updateBcommentsDTO.getBcommentContent().length() > 100) {
                throw new CustomException(CustomErrorCode.COMMENT_LONG_REQUEST);
            }
            else if(updateBcommentsDTO.getBcommentContent().isEmpty()) {
                throw new CustomException(CustomErrorCode.COMMENT_NOT_CONTENT);
            }
            else {
                Bcomments bcomments = bcommentsRepository.findByBcommentNum(bcommentNum);
                bcomments.setBcommentContent(updateBcommentsDTO.getBcommentContent());
                bcommentsRepository.save(bcomments);
                return HttpStatus.OK;
            }
        }
    }

    public HttpStatus delete(int bcommentNum, DeleteBcommentsDTO deleteBcommentsDTO) {
        Bcomments bcomments = bcommentsRepository.findByBcommentNum(bcommentNum);
        if(bcomments.getUserId().equals(deleteBcommentsDTO.getUserId())) {
            bcommentsRepository.delete(bcomments);
            return HttpStatus.OK;
        }
        else {
            throw new CustomException(CustomErrorCode.USER_NOT_IDEQUAL);
        }
    }
}
