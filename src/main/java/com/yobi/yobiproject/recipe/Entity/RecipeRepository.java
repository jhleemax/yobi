package com.yobi.yobiproject.recipe.Entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByRecipeNum(int recipeNum);
    List<Recipe> findAll();
    List<Recipe> findAllByFoodNameContaining(String foodName);
    List<Recipe> findByUserId(String userid);

}
