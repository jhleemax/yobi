// Activity_recipe.java
package com.example.yobi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Activity_recipe extends AppCompatActivity {

    // 데이터
    public static Context context;
    HttpConnectionManager httpConnectionManager;
    String jsonString;

    // 컴포넌트
    Button acButton_home;
    TextView textView_recipe_01;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 컴포넌트 초기화
        textView_recipe_01 = findViewById(R.id.textView_recipe_01);
        acButton_home = findViewById(R.id.appCompatButton_recipe_home);
        floatingActionButton = findViewById(R.id.floatingActionButton_recipe);

        // 이벤트 리스너
        textView_recipe_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_recipe.this, Activity_category.class);
                startActivity(intent);
            }
        });
        acButton_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_recipe.this, Activity_main.class);
                startActivity(intent);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_recipe.this, Activity_recipe_write.class);
                startActivity(intent);
            }
        });

        // api 조회
        httpConnectionManager = new HttpConnectionManager(
                "http://10.0.2.2:8080/api/list",
                "POST"
        );

        Thread getResponseThread = new Thread(() -> {
            jsonString = httpConnectionManager.getResponse();
        });

        context = this;

        RecyclerView recyclerView = findViewById(R.id.recyclerView_recipe);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Thread jsonParsingThread = new Thread(() -> {
            JSONParseManager jsonParseManager = new JSONParseManager(jsonString);
            jsonParseManager.splitJSON();

            RecipeOrder[] recipeOrder = jsonParseManager.getObjectbyRecipeOrder();
            ArrayList<Recipe> recipeDataSet = new ArrayList<>();
            for (int i = 0; i < recipeOrder.length; i++) {
                String seq = recipeOrder[i].getRcpseq();
                String img = recipeOrder[i].getAtt_FILE_NO_MAIN();
                String title = recipeOrder[i].getRcpnm();
                String genre = recipeOrder[i].getRcp_PAT2();
                String amount = recipeOrder[i].getInfo_WGT();
                String time = "60";
                String difficulty = "1인분";
                String ingredient = recipeOrder[i].getRcp_PARTS_DTLS();
                Log.e("MYLOG:", (img + " " + title + " " + genre + " " + amount + " " + time + " " + difficulty + " " + ingredient));

                recipeDataSet.add(new Recipe(img, title, genre, ingredient, seq));
            }

            RecipeViewAdapter recipeViewAdapter = new RecipeViewAdapter(recipeDataSet);
            recyclerView.setAdapter(recipeViewAdapter);

            // 아이템 클릭 리스너 설정
            recipeViewAdapter.setOnItemClickListener((view, position) -> {
                // 아이템 클릭 처리
                Recipe clickedRecipe = recipeDataSet.get(position);
                // 예를 들어, 클릭된 레시피 상세 정보 액티비티 시작
                Intent intent = new Intent(Activity_recipe.this, Activity_recipe_detail.class);
                intent.putExtra("RECIPE", clickedRecipe.getTitle());
                intent.putExtra("SEQ", clickedRecipe.getSeq());
                intent.putExtra("SEPARATOR", "api");
                startActivity(intent);
            });

        });

        try {
            getResponseThread.start();
            getResponseThread.join();

            jsonParsingThread.start();
            jsonParsingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
