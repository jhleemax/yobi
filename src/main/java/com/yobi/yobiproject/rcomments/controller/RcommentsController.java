package com.yobi.yobiproject.rcomments.controller;

import com.yobi.yobiproject.rcomments.dto.RcommentsDTO;
import com.yobi.yobiproject.rcomments.service.RcommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class RcommentsController {
    private final RcommentsService rcommentsService;

    @PostMapping(value = "/recipe/comment/write")
    public ResponseEntity<?> write(@RequestBody RcommentsDTO rcommentsDTO) {
        return ResponseEntity.status(rcommentsService.save(rcommentsDTO)).build();
    }
}
