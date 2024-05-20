package com.yobi.yobiproject.board.controller;

import com.yobi.yobiproject.board.dto.BoardDTO;
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
    @GetMapping(value = "/board/list")
    public ResponseEntity<?> list() { // 커뮤니티 글 전체 반환
        return ResponseEntity.ok().body(boardService.list());
    }
}
