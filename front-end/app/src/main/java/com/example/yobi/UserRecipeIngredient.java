package com.example.yobi;

public class UserRecipeIngredient {
    String
            ingredient,
            count;

    public UserRecipeIngredient(String ingredient, String count) {
        this.ingredient = ingredient;
        this.count = count;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
