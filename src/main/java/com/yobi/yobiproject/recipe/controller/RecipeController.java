package com.yobi.yobiproject.recipe.controller;

import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipe;
import com.yobi.yobiproject.defRecipe.service.DefRecipeService;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.dto.UpdateRecipeDTO;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import com.yobi.yobiproject.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(value = "/recipe/write") // 레시피 글쓰기
    public ResponseEntity<?> write(@RequestBody WriteRecipeDTO writeRecipeDTO){
        recipeService.save(writeRecipeDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/recipe/list")
    public ResponseEntity<?> list() { // 모든 사용자 레시피 정보 반환
        List<Recipe> Send_Data = recipeService.SendRecipe();
        return ResponseEntity.ok().body(Send_Data);
    }

    @GetMapping(value = "/recipe/read/{recipe_num}")
    public ResponseEntity<?> read(@PathVariable("recipe_num") int rcpNum) { // 레시피 상세보기
        Recipe Read_Data = recipeService.ReadRecipe(rcpNum);
        return ResponseEntity.ok().body(Read_Data);
    }

    @GetMapping(value = "/recipe/like/{recipe_num}")
    public ResponseEntity<?> like(@PathVariable("recipe_num") int rcpNum) { // 사용자 레시피 좋아요
        return ResponseEntity.status(recipeService.LikeRecipe(rcpNum)).body(""); // 이렇게 다 바꿔야함(서비스포함) -> 방학때 바꿉시다.
    }

    @GetMapping(value = "/recipe/unlike/{recipe_num}")
    public ResponseEntity<?> unlike(@PathVariable("recipe_num") int rcpNum) { // 사용자 레시피 좋아요 취소
        recipeService.UnLikeRecipe(rcpNum);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/recipe/delete/{recipe_num}")
    public ResponseEntity<?> delete(@PathVariable("recipe_num") int rcpNum) { // 사용자 레시피 삭제
        // 현재 보안 요소 미구현(사용자가 일치하는지 등)
        recipeService.DeleteRecipe(rcpNum);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/recipe/update/{recipe_num}")
    public ResponseEntity<?> update(@PathVariable("recipe_num") int rcpNum,
                                    @RequestBody UpdateRecipeDTO updateRecipeDTO) { // 사용자 레시피 수정
        recipeService.Update(updateRecipeDTO, rcpNum);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/recipe/search/{foodName}")
    public ResponseEntity<?> search(@PathVariable("foodName") String foodName) { // 레시피 통합조회
        return ResponseEntity.ok().body(recipeService.SearchRecipe(foodName));
    }

}
