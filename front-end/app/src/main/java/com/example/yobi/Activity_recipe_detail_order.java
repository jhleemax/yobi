package com.example.yobi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Activity_recipe_detail_order extends AppCompatActivity {

    // Data 객체
    ArrayList<RecipeOrderDetail> recipeOrderDetailArrayList;
    int order_num;

    // 컴포넌트 객체
    AppCompatButton nextButton;
    Spinner difficulty, amount;
    LinearLayout start;
    ImageView imageView, image_ingredient, imageMic;
    TextView step, description, description_sub, description_ingredient, mic01, mic02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Intent 정보 받아오기
        recipeOrderDetailArrayList = (ArrayList<RecipeOrderDetail>) getIntent().getSerializableExtra("dataSet");
        order_num = getIntent().getIntExtra("seq_num", 1);

        // 객체 초기화
        nextButton = (AppCompatButton) findViewById(R.id.appCompatButton_recipe_detail_order_next);
        amount = (Spinner) findViewById(R.id.spinner_recipe_detail_order_amount);
        difficulty = (Spinner) findViewById(R.id.spinner_recipe_detail_order_difficulty);
        start = (LinearLayout) findViewById(R.id.linearLayout_recipe_detail_order_start);
        imageView = (ImageView) findViewById(R.id.imageView_recipe_detail_order);
        step = (TextView) findViewById(R.id.textView_recipe_detail_order_step);
        description = (TextView) findViewById(R.id.textView_recipe_detail_order_description_01);
        description_sub = (TextView) findViewById(R.id.textView_recipe_detail_order_description_02);
        image_ingredient = (ImageView) findViewById(R.id.imageView_recipe_detail_order_description);
        description_ingredient = (TextView) findViewById(R.id.textView_recipe_detail_order_description_03);
        imageMic = (ImageView) findViewById(R.id.imageView_recipe_detail_order_mic);
        mic01 = (TextView) findViewById(R.id.textView_recipe_detail_order_mic_01);
        mic02 = (TextView) findViewById(R.id.textView_recipe_detail_order_mic_02);

        // 비가시 처리
        amount.setVisibility(View.GONE);
        difficulty.setVisibility(View.GONE);
        description_sub.setVisibility(View.GONE);
        image_ingredient.setVisibility(View.GONE);
        description_ingredient.setVisibility(View.GONE);
        imageMic.setVisibility(View.GONE);
        mic01.setVisibility(View.GONE);
        mic02.setVisibility(View.GONE);

        // 다음 버튼 이벤트
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_recipe_detail_order.this, Activity_recipe_detail_order.class);
                intent.putExtra("seq_num", ++order_num);
                intent.putExtra("dataSet", recipeOrderDetailArrayList);
                startActivity(intent);
            }
        });

        // 바인딩
        Thread th1 = new Thread(() -> {
            Bitmap thumbnail = getBitmapFromURL(recipeOrderDetailArrayList.get(order_num - 1).getImg());
            imageView.post(() -> imageView.setImageBitmap(thumbnail));
        });

        Thread th2 = new Thread(() -> {
            step.setText("Step " + Integer.toString(order_num) + "/" + Integer.toString(recipeOrderDetailArrayList.size()));
            description.setText(recipeOrderDetailArrayList.get(order_num - 1).getMainDescription());
        });

        try {
            th1.start();
            th1.join();

            th2.start();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}