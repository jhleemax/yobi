package com.yobi.yobiproject.recipe.controller;

import com.yobi.yobiproject.member.dto.MemberDTO;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import com.yobi.yobiproject.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(value = "/recipe/write")
    public ResponseEntity<?> write(@RequestBody WriteRecipeDTO writeRecipeDTO){
        recipeService.save(writeRecipeDTO);
        return ResponseEntity.noContent().build();
    }

}
