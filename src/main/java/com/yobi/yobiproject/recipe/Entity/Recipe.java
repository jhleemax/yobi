package com.yobi.yobiproject.recipe.Entity;

import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column(name = "recipe_num")
    private int recipeNum;

    @Column(name = "userid")
    private String userId;

    @Column(name = "recipe_category")
    private String recipe_Category;

    @Column(name = "recipe_content")
    private String recipe_Content;

    @Column(name = "recipe_image")
    private String recipe_Image;

    @Column(name = "recipe_nice")
    private int recipe_Nice;

    @Column(name = "foodname")
    private String foodName;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "material")
    private String material;

    @Column(name = "recipe_report")
    private int  recipe_Report;

    @Builder
    public static Recipe toRecipe(WriteRecipeDTO writeRecipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setUserId(writeRecipeDTO.getUserId());
        recipe.setRecipe_Category(writeRecipeDTO.getRecipe_Category());
        recipe.setRecipe_Image(writeRecipeDTO.getRecipe_Image());
        recipe.setRecipe_Content(writeRecipeDTO.getRecipe_Content());
        recipe.setFoodName(writeRecipeDTO.getFoodName());
        recipe.setIngredient(writeRecipeDTO.getIngredient());
        recipe.setMaterial(writeRecipeDTO.getMaterial());
        return recipe;
    }

}
