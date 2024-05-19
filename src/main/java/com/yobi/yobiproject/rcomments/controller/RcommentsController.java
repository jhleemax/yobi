package com.yobi.yobiproject.rcomments.controller;

import com.yobi.yobiproject.rcomments.dto.DeleteRcommentsDTO;
import com.yobi.yobiproject.rcomments.dto.RcommentsDTO;
import com.yobi.yobiproject.rcomments.dto.UpdateRcommentsDTO;
import com.yobi.yobiproject.rcomments.service.RcommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class RcommentsController {
    private final RcommentsService rcommentsService;

    @PostMapping(value = "/recipe/comment/write")
    public ResponseEntity<?> write(@RequestBody RcommentsDTO rcommentsDTO) {
        return ResponseEntity.status(rcommentsService.save(rcommentsDTO)).build();
    }

    @GetMapping(value = "/recipe/comment/list/{recipe_num}")
    public ResponseEntity<?> list(@PathVariable("recipe_num") int rcpNum) {
        return ResponseEntity.ok().body(rcommentsService.read(rcpNum));
    }

    @PatchMapping(value = "/recipe/comment/update/{rcomment_num}")
    public ResponseEntity<?> update(@PathVariable("rcomment_num") int rComNum,
                                    @RequestBody UpdateRcommentsDTO updateRcommentsDTO) { // 수정
        return ResponseEntity.status(rcommentsService.update(rComNum, updateRcommentsDTO)).build();
    }

    @DeleteMapping(value = "/recipe/comment/delete/{rcomment_num}")
    public ResponseEntity<?> delete(@PathVariable("rcomment_num") int rComNum,
                                    @RequestBody DeleteRcommentsDTO deleteRcommentsDTO) { // 삭제
        return ResponseEntity.status(rcommentsService.delete(rComNum, deleteRcommentsDTO)).body("삭제 성공");

    }


}
