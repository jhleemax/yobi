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

}
