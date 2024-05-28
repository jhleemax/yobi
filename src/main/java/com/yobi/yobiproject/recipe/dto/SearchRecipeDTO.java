package com.yobi.yobiproject.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchRecipeDTO {
    private String foodName;
    private String RCPNM;
}
