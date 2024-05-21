package com.example.yobi;

import static com.example.yobi.RecipeViewAdapter.getBitmapFromURL;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yobi.Retrofit.Repository;
import com.example.yobi.Retrofit.dto.RecipeInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_recipe_detail extends AppCompatActivity {
    private Repository repository;
    TextView textView01;
    ImageView mainImage;
    RecipeInfo recipe;

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

        // 전달받은 Intent 값 가져오기
        Intent data_receive;
        data_receive = getIntent();
        String nm = data_receive.getStringExtra("RECIPE");

        // 타이틀 가져오는거 테스트용
        textView01 = (TextView) findViewById(R.id.textview_recipe_detail_title);
        textView01.setText(nm);
        mainImage = (ImageView) findViewById(R.id.imageView_recipe_detail_01);


        repository = new Repository();

        // 특정 레시피 nm으로 가져오기
        repository.getRecipeByNm(nm, new Callback<RecipeInfo>() {
                    @Override
                    public void onResponse(Call<RecipeInfo> call, Response<RecipeInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            recipe = response.body();
                            // 레시피 처리
                            Log.d("Activity_recipe_detail", "Recipe: " + recipe.getRCP_NM());
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeInfo> call, Throwable throwable) {

                    }
                });

        // 특정 레시피 seq로 가져오기 (28자리에 변수 넣기)
        repository.getRecipeBySeq("28", new Callback<RecipeInfo>() {
            @Override
            public void onResponse(Call<RecipeInfo> call, Response<RecipeInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recipe = response.body();
                    // 레시피 처리
                    Log.d("Activity_recipe_detail", "Recipe: " + recipe.getRCP_SEQ());
                }
            }

            @Override
            public void onFailure(Call<RecipeInfo> call, Throwable t) {
                Log.e("Activity_recipe_detail", "Error fetching recipe", t);
            }
        });

//        Bitmap bitmap = getBitmapFromURL(recipe.getATT_FILE_NO_MK());
//        mainImage.setImageBitmap(bitmap);


    } // onCreate()
}