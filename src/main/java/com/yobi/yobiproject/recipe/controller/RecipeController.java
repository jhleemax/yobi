package com.yobi.yobiproject.recipe.controller;

import com.yobi.yobiproject.member.dto.MemberDTO;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import com.yobi.yobiproject.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
@RestController
@ResponseBody
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(value = "/recipe/write") // 조회 방식 미구현
    public ResponseEntity<?> write(@RequestBody WriteRecipeDTO writeRecipeDTO){
        recipeService.save(writeRecipeDTO);
        return ResponseEntity.noContent().build();
    }
}
