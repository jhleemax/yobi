package com.yobi.yobiproject.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WriteRecipeDTO {
    private String userId;
    private String recipe_Category;
    private String foodName;
    private String recipe_Content;
    private String material;
    private String recipe_Image;
    private String ingredient;
}
