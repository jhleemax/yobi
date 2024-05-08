package com.yobi.yobiproject.recipe.Entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByRecipeNum(int recipeNum);
    List<Recipe> findAll();
}
