package com.example.yobi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Activity_my_boards extends AppCompatActivity {

    // 데이터
    String jsonString;
    String userid;
    ArrayList<String> seqs;
    ArrayList<String> imageURLs;
    // 컴포넌트
    RecyclerView recyclerView;
    ConstraintLayout goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_boards);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // userid 초기화(현재는 더미상태)
        userid = "test";
        seqs = new ArrayList<>();
        imageURLs = new ArrayList<>();

        // 컴포넌트 초기화
        recyclerView = findViewById(R.id.recyclerView_my_boards_01);
        goHome = findViewById(R.id.constraintLayout_my_board_home);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_my_boards.this, Activity_main.class);
                startActivity(intent);
            }
        });

        // 데이터 불러오기
        HttpConnectionManager httpConnectionManager = new HttpConnectionManager(
                "http://10.0.2.2:8080/recipe/user/" + userid,
                "GET"
        );

        Thread getResponseThread = new Thread(() -> {
            jsonString = httpConnectionManager.getResponse();
        });

        Thread jsonParsingThread = new Thread(() -> {
            JSONParseManager jsonParseManager3 =  new JSONParseManager(jsonString);
            jsonParseManager3.splitJSON();

            UserRecipe[] userRecipesforSeq = jsonParseManager3.getObjectbyUserRecipe();
            for(int i = 0; i < userRecipesforSeq.length; i++) {
                seqs.add(userRecipesforSeq[i].getRecipeNum());
            }

            // 이미지 처리를 위해 각 레시피별로 api 호출
            for(int i = 0; i < seqs.size(); i++) {
                HttpConnectionManager httpConnectionManager2 = new HttpConnectionManager(
                        "http://10.0.2.2:8080/recipe/read/" + seqs.get(i),
                        "GET"
                );

                String jsonString1 = httpConnectionManager2.getResponse();

                JSONParseManager jsonParseManager2 = new JSONParseManager(jsonString1);
                jsonParseManager2.splitJSON();

                UserRecipe tempRecipe = jsonParseManager2.getObjectbyUserRecipe()[0];

                imageURLs.add(tempRecipe.getRecipeImage01());
            }

            // 리사이클러뷰 데이터 처리
            JSONParseManager jsonParseManager = new JSONParseManager(jsonString);
            jsonParseManager.splitJSON();

            UserRecipe[] userRecipes = jsonParseManager.getObjectbyUserRecipe();
            ArrayList<Board> recipeDataSet = new ArrayList<>();
            for (int i = 0; i < userRecipes.length; i++) {
                String thumbnail = imageURLs.get(i);
//                String thumbnail = "http://10.0.2.2:8080/images/test/2/1_eeca2e12-dc15-4056-adc8-a293787c23a2.jpg";
                Log.e("URL : ", thumbnail);
                String title = userRecipes[i].getFoodName();
                String profileImg = "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00108_1.png";
                String userId = userRecipes[i].getUserId();
                String like = userRecipes[i].getRecipeNice();
                String recipeNum = userRecipes[i].getRecipeNum();

                seqs.add(recipeNum);
                Log.e("MYLOG:", (thumbnail + " " + title + " " + profileImg + " " + userId + " " + like));

                recipeDataSet.add(new Board(thumbnail, title, profileImg, userId, like, recipeNum));
            }

            // 리사이클러뷰 초기화 및 어댑터 탑재
            /*
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
            staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            staggeredGridLayoutManager.setSpanCount(2);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
             */


            // test
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
            recyclerView.setLayoutManager(linearLayoutManager);


            StaggeredRecyclerViewAdaptor staggeredRecyclerViewAdaptor = new StaggeredRecyclerViewAdaptor(recipeDataSet);
            recyclerView.setAdapter(staggeredRecyclerViewAdaptor);

            // 아이템 클릭 리스너 설정
            staggeredRecyclerViewAdaptor.setOnItemClickListener((view, position) -> {
                // 클릭된 레시피 정보 가져오기
                Board clickedRecipe = recipeDataSet.get(position);

                // 더보기 버튼 클릭 처리
                if(view.getId() == R.id.staggered_more) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                    getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // 수정
                            if(item.getItemId() == R.id.update) {
                                Intent intent = new Intent(Activity_my_boards.this, Activity_recipe_write.class);
                                intent.putExtra("recipeNum", clickedRecipe.getRecipeNum());
                                startActivity(intent);
                            } // 삭제
                            else if(item.getItemId() == R.id.delete) {
                            /* 창 흐리게 하기(활성화 시 에러남)
                            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                            layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                            layoutParams.dimAmount = 0.8f;
                            getWindow().setAttributes(layoutParams);
                             */

                                DeleteDialog deleteDialog = new DeleteDialog(Activity_my_boards.this, "해당 게시물을 삭제하시겠습니까?");
                                deleteDialog.setDialogListener(new DialogListener() {
                                    // 예 클릭시
                                    @Override
                                    public void onPositiveClicked() {
                                        HttpConnectionManager httpConnectionManager1 = new HttpConnectionManager(
                                                "http://10.0.2.2:8080/recipe/delete",
                                                "POST"
                                        );

                                        // 파라미터 설정
                                        Log.e("recipeNum", clickedRecipe.getRecipeNum());
                                        Log.e("userId", clickedRecipe.getUserid());
                                        String json =
                                                "{" +
                                                        "\"recipeNum\" : \"" + clickedRecipe.getRecipeNum() + "\"," +
                                                        "\"userId\" : \"" + clickedRecipe.getUserid() + "\"" +
                                                        "}";


                                        Thread th2 = new Thread(() -> {
                                            String result = null;
                                            try {
                                                result = httpConnectionManager1.doPostbyJSON(json);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            //Log.e("RESULT", result);
                                        });

                                        try {
                                            th2.start();
                                            th2.join();

                                            deleteDialog.dismiss();

                                            finish();
                                            Intent intent = getIntent();
                                            startActivity(intent);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    // 아니오 클릭시
                                    @Override
                                    public void onNegativeClicked() {
                                        deleteDialog.dismiss();
                                    }
                                });
                                deleteDialog.show();
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                } else { // 상세보기를 위한 클릭일 경우
                    Intent intent = new Intent(Activity_my_boards.this, Activity_recipe_detail.class);
                    intent.putExtra("RECIPE", clickedRecipe.getTitle());
                    intent.putExtra("SEQ", clickedRecipe.getRecipeNum());
                    intent.putExtra("SEPARATOR", "recipe");
                    startActivity(intent);
                }
            });
        });

        try {
            getResponseThread.start();
            getResponseThread.join();

            jsonParsingThread.start();
            jsonParsingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}