package com.example.yobi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import com.example.yobi.Retrofit.Repository;
import com.example.yobi.Retrofit.dto.RecipeInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_recipe_detail extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        repository = new Repository();

        // 특정 레시피 nm으로 가져오기
        repository.getRecipeByNm("1", new Callback<RecipeInfo>() {
                    @Override
                    public void onResponse(Call<RecipeInfo> call, Response<RecipeInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            RecipeInfo recipe = response.body();
                            // 레시피 처리
                            Log.d("Activity_recipe_detail", "Recipe: " + recipe.getRCP_NM());
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeInfo> call, Throwable throwable) {

                    }
                });

        // 특정 레시피 seq로 가져오기
        repository.getRecipeBySeq("28", new Callback<RecipeInfo>() {
            @Override
            public void onResponse(Call<RecipeInfo> call, Response<RecipeInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RecipeInfo recipe = response.body();
                    // 레시피 처리
                    Log.d("Activity_recipe_detail", "Recipe: " + recipe.getRCP_SEQ());
                }
            }

            @Override
            public void onFailure(Call<RecipeInfo> call, Throwable t) {
                Log.e("Activity_recipe_detail", "Error fetching recipe", t);
            }
        });
    }
}