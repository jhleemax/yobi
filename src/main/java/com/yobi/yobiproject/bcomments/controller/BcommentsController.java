package com.yobi.yobiproject.bcomments.controller;

import com.yobi.yobiproject.bcomments.dto.BcommentsDTO;
import com.yobi.yobiproject.bcomments.dto.DeleteBcommentsDTO;
import com.yobi.yobiproject.bcomments.dto.UpdateBcommentsDTO;
import com.yobi.yobiproject.bcomments.service.BcommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class BcommentsController {
    private final BcommentsService bcommentsService;

    @PostMapping(value = "/board/comment/write")
    public ResponseEntity<?> write(@RequestBody BcommentsDTO bcommentsDTO) {
        return ResponseEntity.status(bcommentsService.save(bcommentsDTO)).build();
    }

    @GetMapping(value = "/board/comment/list/{board_num}")
    public ResponseEntity<?> list(@PathVariable("board_num") int boardNum) {
        return ResponseEntity.ok().body(bcommentsService.read(boardNum));
    }

    @PatchMapping(value = "/board/comment/update/{bcomment_num}")
    public ResponseEntity<?> update(@PathVariable("bcomment_num") int bcommentNum,
                                    @RequestBody UpdateBcommentsDTO updateBcommentsDTO) {
        return ResponseEntity.status(bcommentsService.update(bcommentNum, updateBcommentsDTO)).build();
    }

    @DeleteMapping(value = "/board/comment/delete/{bcomment_num}")
    public ResponseEntity<?> delete(@PathVariable("bcomment_num") int bcommentNum,
                                    @RequestBody DeleteBcommentsDTO deleteBcommentsDTO) {
        return ResponseEntity.status(bcommentsService.delete(bcommentNum, deleteBcommentsDTO)).build();
    }
}
