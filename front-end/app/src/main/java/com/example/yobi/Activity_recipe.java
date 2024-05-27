// Activity_recipe.java
package com.example.yobi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_recipe extends AppCompatActivity {

    public static Context context;

    HttpConnectionManager httpConncectionManager;
    String jsonString;

    Button acButton_home;

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

        httpConncectionManager = new HttpConnectionManager(
                "http://ec2-13-125-237-115.ap-northeast-2.compute.amazonaws.com:8081/api/defrecipe",
                "POST"
        );

        Thread getResponseThread = new Thread(() -> {
            jsonString = httpConncectionManager.getResponse();
        });

        context = this;

        acButton_home = findViewById(R.id.appCompatButton_community_home);
        acButton_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_recipe.this, Activity_main.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView_recipe);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Thread jsonParsingThread = new Thread(() -> {
            JSONParseManager jsonParseManager = new JSONParseManager(jsonString);
            jsonParseManager.splitJSON();

            RecipeOrder[] recipeOrder = jsonParseManager.getObject();
            ArrayList<Recipe> recipeDataSet = new ArrayList<>();
            for (int i = 0; i < recipeOrder.length; i++) {
                String img = recipeOrder[i].getAtt_FILE_NO_MAIN();
                String title = recipeOrder[i].getRcpnm();
                String genre = recipeOrder[i].getRcp_PAT2();
                String amount = recipeOrder[i].getInfo_WGT();
                String time = "60";
                String difficulty = "1인분";
                String ingredient = recipeOrder[i].getRcp_PARTS_DTLS();
                Log.e("MYLOG:", (img + " " + title + " " + genre + " " + amount + " " + time + " " + difficulty + " " + ingredient));

                recipeDataSet.add(new Recipe(img, title, genre, ingredient));
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
