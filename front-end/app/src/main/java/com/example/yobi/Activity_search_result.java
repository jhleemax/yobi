package com.example.yobi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Activity_search_result extends AppCompatActivity {

    String jsonString;
    Button btn_back;
    SearchView sv_recipe;
    String recive_keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recive_keyword = getIntent().getStringExtra("foodName");

        btn_back = findViewById(R.id.appCompatButton_search_result_backspace);
        sv_recipe = findViewById(R.id.searchView_search_result);
        sv_recipe.setQuery(recive_keyword, false);

        // SearchView에 OnQueryTextListener 설정
        sv_recipe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색 버튼을 누르면 호출됨
                String keyword = query.trim();
                Intent intent = new Intent(Activity_search_result.this, Activity_search_result.class);
                intent.putExtra("foodName", keyword);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색 쿼리가 변경될 때 호출됨
                return false;
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 서버와의 통신을 별도의 쓰레드에서 실행
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jsonString = sendPostRequest("http://10.0.2.2:8080/recipe/search", recive_keyword);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setupRecyclerView(jsonString);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String sendPostRequest(String urlString, String keyword) throws IOException {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String result = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("RCPNM", keyword);
            jsonParam.put("foodName", keyword);

            Log.d("sendPostRequest", "Request Body: " + jsonParam.toString());

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonParam.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            Log.d("sendPostRequest", "Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }
                result = response.toString();
                Log.d("sendPostRequest", "Response: " + result);
            } else {
                Log.e("HTTP Error", "Response Code: " + responseCode);
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }
                Log.e("HTTP Error", "Response Message: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }

    private void setupRecyclerView(String jsonString) {
        if (jsonString == null) {
            Log.e("setupRecyclerView", "jsonString is null");
            return;
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView_search_result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        JSONParseManager jsonParseManager = new JSONParseManager(jsonString);
        jsonParseManager.splitJSON();

        RecipeOrder[] recipeOrder = jsonParseManager.getObjectbyRecipeOrder();
        ArrayList<Recipe> recipeDataSet = new ArrayList<>();
        for (RecipeOrder order : recipeOrder) {
            String seq = order.getRcpseq();
            String img = order.getAtt_FILE_NO_MAIN();
            String title = order.getRcpnm();
            String genre = order.getRcp_PAT2();
            String amount = order.getInfo_WGT();
            String time = "60";
            String difficulty = "1인분";
            String ingredient = order.getRcp_PARTS_DTLS();
            Log.e("MYLOG:", img + " " + title + " " + genre + " " + amount + " " + time + " " + difficulty + " " + ingredient);

            recipeDataSet.add(new Recipe(img, title, genre, ingredient, seq));
        }

        RecipeViewAdapter recipeViewAdapter = new RecipeViewAdapter(recipeDataSet);
        recyclerView.setAdapter(recipeViewAdapter);

        // 아이템 클릭 리스너 설정
        recipeViewAdapter.setOnItemClickListener((view, position) -> {
            // 아이템 클릭 처리
            Recipe clickedRecipe = recipeDataSet.get(position);
            // 예를 들어, 클릭된 레시피 상세 정보 액티비티 시작
            Intent intent = new Intent(Activity_search_result.this, Activity_recipe_detail.class);
            intent.putExtra("RECIPE", clickedRecipe.getTitle());
            intent.putExtra("SEQ", clickedRecipe.getSeq());
            intent.putExtra("SEPARATOR", "api");
            startActivity(intent);
        });
    }
}
