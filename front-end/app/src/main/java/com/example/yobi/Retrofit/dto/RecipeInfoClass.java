package com.example.yobi.Retrofit.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeInfoClass {
    // 데이터를 받을 모델 클래스
    @SerializedName("recipeinfo")
    @Expose
    private RecipeInfo[] recipeInfo;

    public RecipeInfo[] getRecipeInfo() {
        return recipeInfo;
    }

    public void setRecipeInfo(RecipeInfo[] recipeInfo) {
        this.recipeInfo = recipeInfo;
    }

    // toString도 오버라이딩 하면 좋다
}
