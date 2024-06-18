package com.example.yobi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Activity_community extends AppCompatActivity {
    // 데이터 오브젝트
    HttpConnectionManager httpConnectionManager;
    String jsonString;

    // 컴포넌트
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_community);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 컴포넌트 초기화
        floatingActionButton = findViewById(R.id.floatingActionButton_community);

        // 컴포넌트 이벤트 리스너
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_community.this, Activity_community_write.class);
                startActivity(intent);
            }
        });

        // 커뮤니티 데이터 불러오기
        httpConnectionManager = new HttpConnectionManager(
                "http://10.0.2.2:8080/board/list",
                "GET"
        );

        Thread getResponseThread = new Thread(() -> {
            jsonString = httpConnectionManager.getResponse();
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView_community_01);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // 데이터가 없을 경우
        if(jsonString == null) {

        } else { // 있을 경우
            JSONParseManager jsonParseManager = new JSONParseManager(jsonString);

        }
    }
}