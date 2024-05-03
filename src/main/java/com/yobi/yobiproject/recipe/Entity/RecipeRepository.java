package com.yobi.yobiproject.recipe.Entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    //Recipe findByUserId(String userId); //setter명, dto명?
    Recipe findByRecipeNum(int recipeNum);
}
