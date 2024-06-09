package com.yobi.yobiproject.recipe.Entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> { // Integer는 기본키 타입
    long countByUserId(String userId); //사용자가 작성한 레시피 개수 가져오기
    Recipe findByRecipeNum(int recipeNum);
    List<Recipe> findAll();
    List<Recipe> findAllByFoodNameContaining(String foodName);
    List<Recipe> findByUserId(String userid);
    List<Recipe> findAllByRecipeNumIn(List<Integer> rcpNums);
}
