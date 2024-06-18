package com.example.yobi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_main extends AppCompatActivity {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    Button acButton_recipe, acButton_community, acButton_my;
    SearchView sv_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 권한 요청 함수
        requestAudioPermission();

        acButton_my = findViewById(R.id.appCompatButton_main_my);
        acButton_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_main.this, Activity_my.class);
                startActivity(intent);
            }
        });

        // 서치뷰 터치시 이동
        sv_search = findViewById(R.id.searchView_community);
        sv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_main.this, Activity_search.class);
                startActivity(intent);
            }
        });
        acButton_recipe = findViewById(R.id.appCompatButton_main_recipe);
        acButton_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_main.this, Activity_recipe.class);
                startActivity(intent);
            }
        });

        acButton_community = findViewById(R.id.appCompatButton_main_community);
        acButton_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_main.this, Activity_community.class);
                startActivity(intent);
            }
        });

    }
    // 음성 권환 요청
    private void requestAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        } else {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "마이크 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}