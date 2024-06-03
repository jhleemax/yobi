package com.example.yobi;

public class RecipeIngredient {
    public RecipeIngredient(String ingredient_name, String ingredient_amount, String ingredient_required) {
        this.ingredient_name = ingredient_name;
        this.ingredient_amount = ingredient_amount;
        this.ingredient_required = ingredient_required;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public String getIngredient_amount() {
        return ingredient_amount;
    }

    public String getIngredient_required() {
        return ingredient_required;
    }

    String
        ingredient_name,
        ingredient_amount,
        ingredient_required;
}
