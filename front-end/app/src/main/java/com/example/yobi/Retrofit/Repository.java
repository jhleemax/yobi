package com.example.yobi.Retrofit;

import com.example.yobi.Retrofit.RetrofitClient;
import com.example.yobi.Retrofit.dto.RecipeInfo;
import com.example.yobi.Retrofit.RecipeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {
    private RecipeService recipeService;
    private static final String BASE_URL = "localhost:8080/";

    public Repository() {
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);
        recipeService = retrofit.create(RecipeService.class);
    }

    public void getRecipeByNm(String nm, Callback<RecipeInfo> callback) {
        Call<RecipeInfo> call = recipeService.getRecipeByNm(nm);
        call.enqueue(callback);
    }

    public void getRecipeBySeq(String seq, Callback<RecipeInfo> callback) {
        Call<RecipeInfo> call = recipeService.getRecipeBySeq(seq);
        call.enqueue(callback);
    }
}
