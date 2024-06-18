package com.example.yobi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yobi.Retrofit.Repository;
import com.example.yobi.Retrofit.dto.RecipeInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_recipe_detail extends AppCompatActivity {
    private Repository repository;
    ArrayList<RecipeOrderDetail> recipeOrderDetails;
    TextView textView01, textViewIngredient, textViewTools;
    ImageView mainImage;
    TextView description, profileName, profileDescription;
    LinearLayout linearLayout01;
    CircleImageView profileImg;
    AppCompatButton follow, start;
    RecyclerView ingredient, tools, order;

    RecipeInfo recipe;
    HttpConnectionManager httpConnectionManager;
    String jsonString;
    RecipeOrder recipeOrder;
    Thread dataBindingThread;
    Button backButton;

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
        String recipe_seq = data_receive.getStringExtra("SEQ"); // 시퀀스 가져오기

        // 타이틀 가져오는거 테스트용
        textView01 = (TextView) findViewById(R.id.textview_recipe_detail_title);
        textView01.setText(nm);
        mainImage = (ImageView) findViewById(R.id.imageView_recipe_detail_01);
        description = (TextView) findViewById(R.id.textView_recipe_detail_description);
        linearLayout01 = (LinearLayout) findViewById(R.id.linearLayout_recipe_detail_01);
        profileImg = (CircleImageView) findViewById(R.id.imageView_recipe_detail_profile);
        profileName = (TextView) findViewById(R.id.textView_recipe_detail_profile_name);
        profileDescription = (TextView) findViewById(R.id.textView_recipe_detail_profile_description);
        follow = (AppCompatButton) findViewById(R.id.AppCompatButton);
        start = (AppCompatButton) findViewById(R.id.button_recipe_detail_start);

        textViewIngredient = (TextView) findViewById(R.id.textView_recipe_detail_ingredient);
        textViewTools = (TextView) findViewById(R.id.textView_recipe_detail_tools);
        ingredient = (RecyclerView) findViewById(R.id.recyclerView_recipe_detail_ingredient);
        tools = (RecyclerView) findViewById(R.id.recyclerView_recipe_detail_tools);
        order = (RecyclerView) findViewById(R.id.recyclerView_recipe_detail_order);
        backButton = (Button) findViewById(R.id.appCompatButton_recipe_detail_backspace);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_recipe_detail.this, Activity_recipe_detail_order.class);
                intent.putExtra("dataSet", recipeOrderDetails);
                intent.putExtra("order_num", 1);
                startActivity(intent);
            }
        });

        httpConnectionManager = new HttpConnectionManager(
                "http://10.0.2.2:8080/api/read/" + recipe_seq,
                "GET"
        );

        Thread getResponseThread = new Thread(() -> {
            jsonString = httpConnectionManager.getResponse();
        });

        //RecyclerView recyclerView = findViewById(R.id.recyclerView_recipe_detail_ingredient);

        Thread jsonParsingThread = new Thread(() -> {

            JSONParseManager jsonParseManager = new JSONParseManager(jsonString);
            jsonParseManager.splitJSON();

            recipeOrder = jsonParseManager.getObjectbyRecipeOrder()[0];

            // 레시피 재료 파싱 파트(현재 비활성화)
            /*
            String json = recipeOrder.getRcp_PARTS_DTLS();
            String[] ingredients = json.split("\n");
            ArrayList<String> lineSplit = new ArrayList<String>();
            ArrayList<String> commaSplit = new ArrayList<String>();
            HashMap<String, String> result = new HashMap<String, String>();

            for(String s : ingredients) {
                if(s.indexOf(":") != -1) {
                    s = s.split(":")[1].trim();
                    lineSplit.add(s);
                    Log.e("case 1", s);
                } else if(!(s.startsWith("●"))) {
                    lineSplit.add(s);
                    Log.e("case 2", s);
                }
            }

            for(String s : lineSplit) {
                for(String temp : s.split(",")) {
                    commaSplit.add(temp.trim());
                    Log.e("split by ,", temp.trim());
                }
            }

            ArrayList<RecipeIngredient> dataSet = new ArrayList<>();
            for(String s : commaSplit) {
//                result.put(s.split(" ")[0], s.split(" ")[1]);
                if(s.split(" ").length != 1 && s.split(" ")[1].indexOf("g") != -1) {
                    Log.e("RecipeIngredient : ", s.split(" ")[0] + " : " + s.split(" ")[1].trim());
                    dataSet.add(new RecipeIngredient(s.split(" ")[0], s.split(" ")[1], "O"));
                }
            }

            RecipeIngredientViewAdaptor recipeIngredientViewAdaptor = new RecipeIngredientViewAdaptor(dataSet);
            recyclerView.setAdapter(recipeIngredientViewAdaptor);
             */

            // 조리 순서 파싱(노가다를 안 하는 경우는 문자열로 조작)
            recipeOrderDetails = new ArrayList<RecipeOrderDetail>();

            Log.e("RecipeDetail 06", recipeOrder.getManual06());
            Log.e("RecipeDetail 06 img", recipeOrder.getManual_IMG06());
            Log.e("RecipeDetail 07", recipeOrder.getManual07());
            Log.e("RecipeDetail 07 img", recipeOrder.getManual_IMG07());
            Log.e("RecipeDetail 07 equals", Boolean.toString(recipeOrder.getManual07().equals("")));
            Log.e("RecipeDetail 07 isEmpty", Boolean.toString(recipeOrder.getManual07().isEmpty()));
            if(!(recipeOrder.getManual01().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG01(), recipeOrder.getManual01()));
            }
            if(!(recipeOrder.getManual02().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG02(), recipeOrder.getManual02()));
            }
            if(!(recipeOrder.getManual03().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG03(), recipeOrder.getManual03()));
            }
            if(!(recipeOrder.getManual04().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG04(), recipeOrder.getManual04()));
            }
            if(!(recipeOrder.getManual05().isEmpty())) {
                Log.e("RecipeDetail05", "RUN");
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG05(), recipeOrder.getManual05()));
            }
            if(!(recipeOrder.getManual06().isEmpty())) {
                Log.e("RecipeDetail06", "RUN");
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG06(), recipeOrder.getManual06()));
            }
            if(!(recipeOrder.getManual07().isEmpty())) {
                Log.e("RecipeDetail07", "RUN");
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG07(), recipeOrder.getManual07()));
            }
            if(!(recipeOrder.getManual08().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG08(), recipeOrder.getManual08()));
            }
            if(!(recipeOrder.getManual09().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG09(), recipeOrder.getManual09()));
            }
            if(!(recipeOrder.getManual10().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG10(), recipeOrder.getManual10()));
            }
            if(!(recipeOrder.getManual11().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG11(), recipeOrder.getManual11()));
            }
            if(!(recipeOrder.getManual12().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG12(), recipeOrder.getManual12()));
            }
            if(!(recipeOrder.getManual13().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG13(), recipeOrder.getManual13()));
            }
            if(!(recipeOrder.getManual14().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG14(), recipeOrder.getManual14()));
            }
            if(!(recipeOrder.getManual15().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG15(), recipeOrder.getManual15()));
            }
            if(!(recipeOrder.getManual16().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG16(), recipeOrder.getManual16()));
            }
            if(!(recipeOrder.getManual17().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG17(), recipeOrder.getManual17()));
            }
            if(!(recipeOrder.getManual18().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG18(), recipeOrder.getManual18()));
            }
            if(!(recipeOrder.getManual19().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG19(), recipeOrder.getManual19()));
            }
            if(!(recipeOrder.getManual20().isEmpty())) {
                recipeOrderDetails.add(new RecipeOrderDetail(recipeOrder.getManual_IMG20(), recipeOrder.getManual20()));
            }

            RecipeOrderDetailViewAdaptor recipeOrderDetailViewAdaptor = new RecipeOrderDetailViewAdaptor(recipeOrderDetails);
            order.setAdapter(recipeOrderDetailViewAdaptor);
        });

        // api 레시피일 경우
        if(data_receive.getStringExtra("SEPARATOR").equals("api")) {
                // 썸네일
                dataBindingThread = new Thread(() -> {
                Bitmap thumbnail = getBitmapFromURL(recipeOrder.getAtt_FILE_NO_MK());
                mainImage.post(() -> mainImage.setImageBitmap(thumbnail));
//            Log.e("IMAGE_URL", recipeOrder.getAtt_FILE_NO_MK());

                // 비가시 처리
                linearLayout01.setVisibility(View.GONE);
                textViewTools.setVisibility(View.GONE);
                textViewIngredient.setVisibility(View.GONE);
                ingredient.setVisibility(View.GONE);
                tools.setVisibility(View.GONE);

                // 프로필 이미지
                profileImg.setBackgroundColor(getColor(R.color.main_theme));
                Drawable profile = getDrawable(R.drawable.yobi_profile);
                profileImg.setImageDrawable(profile);

                // 프로필 이름
                profileName.setText("YOBI Official");
                profileDescription.setText("YOBI 공식 프로필 입니다");

                // 레시피 설명
                description.setText(recipeOrder.getRcp_NA_TIP());

                //LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
                //recyclerView.setLayoutManager(linearLayoutManager);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
                order.setLayoutManager(linearLayoutManager);
            });
        }

        try {
            getResponseThread.start();
            getResponseThread.join();

            jsonParsingThread.start();
            jsonParsingThread.join();

            dataBindingThread.start();
            dataBindingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    } // onCreate()

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