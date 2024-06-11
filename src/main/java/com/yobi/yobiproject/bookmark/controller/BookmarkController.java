package com.yobi.yobiproject.bookmark.controller;

import com.yobi.yobiproject.bookmark.dto.BookmarkDTO;
import com.yobi.yobiproject.bookmark.service.BookmarkService;
import com.yobi.yobiproject.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping(value = "/bookmark/save")
    public ResponseEntity<?> save(@RequestBody BookmarkDTO bookmarkDTO) {
        return ResponseEntity.status(bookmarkService.save(bookmarkDTO)).build();
    }

    @GetMapping(value = "/bookmark/list/{userId}")
    public ResponseEntity<?> list(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(bookmarkService.list(userId));
    }


}
