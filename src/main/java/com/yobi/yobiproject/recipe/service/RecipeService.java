package com.yobi.yobiproject.recipe.service;

import com.yobi.yobiproject.member.dto.MemberDTO;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.Entity.RecipeRepository;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public void save(WriteRecipeDTO writeRecipeDTO) {
        Recipe.toRecipe(writeRecipeDTO);
    }
}
