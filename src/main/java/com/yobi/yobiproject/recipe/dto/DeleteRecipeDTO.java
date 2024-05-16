package com.yobi.yobiproject.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteRecipeDTO {
    private int recipeNum;
    private String userId;
}
