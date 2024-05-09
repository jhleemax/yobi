package com.yobi.yobiproject.recipe.service;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.Entity.RecipeRepository;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public void save(WriteRecipeDTO writeRecipeDTO) {
        Recipe recipe = Recipe.toRecipe(writeRecipeDTO);
        recipeRepository.save(recipe);
    }

    public List<Recipe> SendRecipe() { // 모든 사용자 레시피
        List<Recipe> recipe = recipeRepository.findAll();
        return recipe;
    }

    public Recipe ReadRecipe(int rcpnum) { // 사용자 레시피 상세보기
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        return recipe;
    }

    public void LikeRecipe(int rcpnum) { // 사용자 레시피 좋아요 추가
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipe.setRecipeNice(recipe.getRecipeNice() + 1);
        recipeRepository.save(recipe);
    }

    public void UnLikeRecipe(int rcpnum) {
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipe.setRecipeNice(recipe.getRecipeNice() - 1);
        recipeRepository.save(recipe);
    }

    public void DeleteRecipe(int rcpnum) {
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipeRepository.delete(recipe);
    }

}
