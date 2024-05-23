package com.example.yobi.Retrofit;

import com.example.yobi.Retrofit.dto.RecipeInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeService {

    @GET("api/search/{nm}")
    Call<RecipeInfo> getRecipeByNm(@Path("nm") String nm);

    @GET("api/read/{seq}")
    Call<RecipeInfo> getRecipeBySeq(@Path("seq") String seq);
}
