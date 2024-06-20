package com.yobi.yobiproject.board.controller;

import com.yobi.yobiproject.board.dto.BoardDTO;
import com.yobi.yobiproject.board.dto.DeleteBoardDTO;
import com.yobi.yobiproject.board.dto.UpdateBoardDTO;
import com.yobi.yobiproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping(value = "/board/write")
    public ResponseEntity<?> write(@RequestBody BoardDTO boardDTO) { // 커뮤니티 글 작성
        return ResponseEntity.status(boardService.save(boardDTO)).build();
    }
    @PostMapping(value = "/board/list")
    public ResponseEntity<?> list() { // 커뮤니티 글 전체 반환
        return ResponseEntity.ok().body(boardService.list());
    }

    @GetMapping(value = "/board/read/{board_num}")
    public ResponseEntity<?> read(@PathVariable("board_num") int boardNum) {
        return ResponseEntity.ok().body(boardService.read(boardNum));
    }

    @DeleteMapping(value = "/board/delete")
    public ResponseEntity<?> delete(@RequestBody DeleteBoardDTO deleteBoardDTO) {
        return ResponseEntity.status(boardService.delete(deleteBoardDTO)).body("삭제 성공");
    }

    @PatchMapping(value = "/board/update/{board_num}")
    public ResponseEntity<?> update(@PathVariable("board_num") int boardNum,
                                    @RequestBody UpdateBoardDTO updateBoardDTO) {
        return ResponseEntity.status(boardService.update(boardNum, updateBoardDTO)).build();
    }
}
