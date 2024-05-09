package com.yobi.yobiproject.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WriteRecipeDTO {
    private String userId;
    private String recipeCategory;
    private String foodName;
    private String recipeContent;
    private String material;
    private String recipeImage;
    private String ingredient;
}
