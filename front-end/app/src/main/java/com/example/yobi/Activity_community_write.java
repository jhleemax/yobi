package com.example.yobi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class Activity_community_write extends AppCompatActivity {
    // 데이터 오브젝트
    HttpConnectionManager httpConnectionManager;
    Uri imageUri;

    // 컴포넌트
    ImageView imageView;
    EditText title, description;
    Spinner category;
    AppCompatButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_community_write);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 컴포넌트 연결
        imageView = findViewById(R.id.imageView_community_write_addition);
        title = findViewById(R.id.editText_community_write_title);
        description = findViewById(R.id.editText_community_write_description);
        category = findViewById(R.id.spinner_community_write_category);
        submit = findViewById(R.id.appCompatButton_community_write_submit);

        // Spinner 초기화
        String[] array = getResources().getStringArray(R.array.category);

        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.spinner_category_item, array);
        adapter.setDropDownViewResource(R.layout.spinner_category_item);
        category.setAdapter(adapter);

        // ImageView 액션 이벤트
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityResult.launch(intent);
            }
        });

        // Submit 버튼 액션 이벤트
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // api url 설정
                StringBuilder postData = new StringBuilder();
                Thread th1 = new Thread(() -> {
                    httpConnectionManager = new HttpConnectionManager(
                            "http://10.0.2.2:8080/board/write",
                            "POST"
                    );

                    // 파라미터 설정
                    Map<String, String> params = new LinkedHashMap<>();
                    params.put("userId", "YobiTest");
                    params.put("boardContent", description.getText().toString());
                    params.put("boardCategory", category.getSelectedItem().toString());
                    params.put("boardTitle", title.getText().toString());
                    params.put("boardImage", imageView.getDrawable().toString());

                    //StringBuilder postData = new StringBuilder();
                    for(Map.Entry<String, String> param : params.entrySet()) {
                        if(postData.length() != 0) postData.append('&');
                        try {
                            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                            postData.append('=');
                            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Thread th2 = new Thread(() -> {
                    try {
                        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                        String result = httpConnectionManager.doPostbyParams(postDataBytes);
                        Log.e("RESULT", result);
                    } catch (UnsupportedEncodingException e) {
                        Log.e("Activity_community_write", e.toString());
                    }
                });

                try {
                    th1.start();
                    th1.join();

                    th2.start();
                    th2.join();
                } catch (InterruptedException e) {
                    Log.e("Activity_community_write", e.toString());
                }
            }
        });
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == RESULT_OK && o.getData() != null) {
                imageUri = o.getData().getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Log.e("Activity_community_write", e.toString());
                } catch (IOException e) {
                    Log.e("Activity_community_write", e.toString());
                }
            }
        }
    });
}