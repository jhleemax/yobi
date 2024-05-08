package com.example.yobi;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

public class Activity_recipe extends AppCompatActivity {

    public static Context context;

    String jsonString = "{\n" +
            "        \"att_FILE_NO_MAIN\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00108_1.png\",\n" +
            "        \"info_NA\": \"76\",\n" +
            "        \"rcp_SEQ\": \"108\",\n" +
            "        \"info_PRO\": \"1\",\n" +
            "        \"info_ENG\": \"60\",\n" +
            "        \"rcp_PAT2\": \"후식\",\n" +
            "        \"info_WGT\": \"250\",\n" +
            "        \"info_CAR\": \"14\",\n" +
            "        \"rcp_NM\": \"오렌지와 당근 만남주스\",\n" +
            "        \"rcp_WAY2\": \"기타\",\n" +
            "        \"att_FILE_NO_MK\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00108_1.png\",\n" +
            "        \"manual01\": \"1. 오렌지는 깨끗이 씻어 껍질을 벗긴다.\",\n" +
            "        \"manual03\": \"3. 당근, 오렌지, 물(50ml)을 믹서에 곱게 간다.\",\n" +
            "        \"manual05\": \"5. 주스를 살얼음이 생길 만큼 시원하게 냉동한다.\",\n" +
            "        \"manual_IMG05\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00108_5.jpg\",\n" +
            "        \"manual_IMG02\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00108_2.jpg\",\n" +
            "        \"manual_IMG03\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00108_3.jpg\",\n" +
            "        \"manual02\": \"2. 당근은 깨끗이 씻어 작은 토막으로 썬다.\",\n" +
            "        \"manual_IMG04\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00108_4.jpg\",\n" +
            "        \"rcp_PARTS_DTLS\": \"오렌지 당근펀치\\n당근 100g(1/2개), 오렌지 100g(1/2개)\",\n" +
            "        \"manual_IMG01\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00108_1.jpg\",\n" +
            "        \"manual06\": \"6. 컵에 담아 마무리한다.\",\n" +
            "        \"info_FAT\": \"0\",\n" +
            "        \"manual04\": \"4. 믹서에 갈아낸 주스는 고운 체로 거른다.\",\n" +
            "        \"hash_TAG\": \"\",\n" +
            "        \"manual_IMG13\": \"\",\n" +
            "        \"manual11\": \"\",\n" +
            "        \"manual14\": \"\",\n" +
            "        \"manual08\": \"\",\n" +
            "        \"manual_IMG07\": \"\",\n" +
            "        \"manual_IMG09\": \"\",\n" +
            "        \"manual07\": \"\",\n" +
            "        \"manual_IMG10\": \"\",\n" +
            "        \"manual_IMG08\": \"\",\n" +
            "        \"manual09\": \"\",\n" +
            "        \"manual_IMG06\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00108_6.jpg\",\n" +
            "        \"manual10\": \"\",\n" +
            "        \"manual_IMG12\": \"\",\n" +
            "        \"manual_IMG11\": \"\",\n" +
            "        \"manual13\": \"\",\n" +
            "        \"manual12\": \"\",\n" +
            "        \"manual17\": \"\",\n" +
            "        \"manual_IMG15\": \"\",\n" +
            "        \"manual_IMG18\": \"\",\n" +
            "        \"manual19\": \"\",\n" +
            "        \"manual_IMG14\": \"\",\n" +
            "        \"manual20\": \"\",\n" +
            "        \"rcp_NA_TIP\": \"시판되는 대부분의 주스는 가공되고 농축되는 과정에서 나트륨이 첨가되는 경우가 있어 과일, 채소를 직접 갈아 마시면 나트륨 섭취를 줄일 수 있어요.\",\n" +
            "        \"manual_IMG17\": \"\",\n" +
            "        \"manual_IMG20\": \"\",\n" +
            "        \"manual15\": \"\",\n" +
            "        \"manual_IMG16\": \"\",\n" +
            "        \"manual18\": \"\",\n" +
            "        \"manual_IMG19\": \"\",\n" +
            "        \"manual16\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"att_FILE_NO_MAIN\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00111_2.png\",\n" +
            "        \"info_NA\": \"12.5\",\n" +
            "        \"rcp_SEQ\": \"111\",\n" +
            "        \"info_PRO\": \"2.1\",\n" +
            "        \"info_ENG\": \"73.4\",\n" +
            "        \"rcp_PAT2\": \"일품\",\n" +
            "        \"info_WGT\": \"\",\n" +
            "        \"info_CAR\": \"12.7\",\n" +
            "        \"rcp_NM\": \"칠곡석류국수\",\n" +
            "        \"rcp_WAY2\": \"끓이기\",\n" +
            "        \"att_FILE_NO_MK\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00111_1.png\",\n" +
            "        \"manual01\": \"1. 잣, 아몬드, 해바라기씨, 호두, 호박씨는 다진다.\",\n" +
            "        \"manual03\": \"3. 석류는 물 600g와 함께 믹서에 갈고 체에 걸러 즙을 낸다. 그릇에 면을 담고 차갑게 식힌 석류즙을 붓고 견과류, 채 썬 오이를 고명으로 올린다.\",\n" +
            "        \"manual05\": \"\",\n" +
            "        \"manual_IMG05\": \"\",\n" +
            "        \"manual_IMG02\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00111_2.png\",\n" +
            "        \"manual_IMG03\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00111_3.png\",\n" +
            "        \"manual02\": \"2. 끓는 물에 저염소금과 식초를 넣은 후 소면을 삶은 후 체에 건져 물기를\\n뺀다.\",\n" +
            "        \"manual_IMG04\": \"\",\n" +
            "        \"rcp_PARTS_DTLS\": \"●주재료 : 소면 160g, 저염소금 4g(1작은술), 식초 8g(1/2작은술)\\n●소스 : 석류 200g(1/2개)\\n●장식 : 잣 4g(1작은술), 아몬드 4g(1작은술), 해바라기씨 4g(1작은술), 호두 4g(1작은술), 호박씨 4g(1작은술), 오이 20g(1/4개)\",\n" +
            "        \"manual_IMG01\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00111_1.png\",\n" +
            "        \"manual06\": \"\",\n" +
            "        \"info_FAT\": \"1.6\",\n" +
            "        \"manual04\": \"\",\n" +
            "        \"hash_TAG\": \"저염소금\",\n" +
            "        \"manual_IMG13\": \"\",\n" +
            "        \"manual11\": \"\",\n" +
            "        \"manual14\": \"\",\n" +
            "        \"manual08\": \"\",\n" +
            "        \"manual_IMG07\": \"\",\n" +
            "        \"manual_IMG09\": \"\",\n" +
            "        \"manual07\": \"\",\n" +
            "        \"manual_IMG10\": \"\",\n" +
            "        \"manual_IMG08\": \"\",\n" +
            "        \"manual09\": \"\",\n" +
            "        \"manual_IMG06\": \"\",\n" +
            "        \"manual10\": \"\",\n" +
            "        \"manual_IMG12\": \"\",\n" +
            "        \"manual_IMG11\": \"\",\n" +
            "        \"manual13\": \"\",\n" +
            "        \"manual12\": \"\",\n" +
            "        \"manual17\": \"\",\n" +
            "        \"manual_IMG15\": \"\",\n" +
            "        \"manual_IMG18\": \"\",\n" +
            "        \"manual19\": \"\",\n" +
            "        \"manual_IMG14\": \"\",\n" +
            "        \"manual20\": \"\",\n" +
            "        \"rcp_NA_TIP\": \"견과류에는 불포화 지방산이 풍부하게 함유되어 있어, 고지혈증 등 혈류개선에 효과가 있어 성인병 예방에 좋다. 짠맛을 대신하여 석류의 새콤달콤한 맛을 활용하고, 볶은 견과류로 고소한 맛과 풍미를 더하였다.\",\n" +
            "        \"manual_IMG17\": \"\",\n" +
            "        \"manual_IMG20\": \"\",\n" +
            "        \"manual15\": \"\",\n" +
            "        \"manual_IMG16\": \"\",\n" +
            "        \"manual18\": \"\",\n" +
            "        \"manual_IMG19\": \"\",\n" +
            "        \"manual16\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"att_FILE_NO_MAIN\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00113_2.png\",\n" +
            "        \"info_NA\": \"18.5\",\n" +
            "        \"rcp_SEQ\": \"113\",\n" +
            "        \"info_PRO\": \"3.4\",\n" +
            "        \"info_ENG\": \"54.3\",\n" +
            "        \"rcp_PAT2\": \"일품\",\n" +
            "        \"info_WGT\": \"\",\n" +
            "        \"info_CAR\": \"4.9\",\n" +
            "        \"rcp_NM\": \"블랙빈 곤약국수\",\n" +
            "        \"rcp_WAY2\": \"끓이기\",\n" +
            "        \"att_FILE_NO_MK\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00113_1.png\",\n" +
            "        \"manual01\": \"1. 검은콩을 1시간 찬물에 담가 불린 후 삶아 익힌 후 불순물을 제거하고 흑임자, 생수, 삶은 콩, 호두를 믹서에 갈아 살엄음이 생기도록 냉동실에 넣는다.\",\n" +
            "        \"manual03\": \"3. 실곤약은 끓는 물에 데치고 찬물에 헹궈 그릇에 담은 후 콩물을 담고 오이, 토마토, 달걀을 고명으로 올려 완성한다.\",\n" +
            "        \"manual05\": \"\",\n" +
            "        \"manual_IMG05\": \"\",\n" +
            "        \"manual_IMG02\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00113_4.png\",\n" +
            "        \"manual_IMG03\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00113_6.png\",\n" +
            "        \"manual02\": \"2. 오이는 돌려 깎아 채 썰고, 토마토는 1/8크기로 썰고, 달걀은 삶아 반으로 자른다.\",\n" +
            "        \"manual_IMG04\": \"\",\n" +
            "        \"rcp_PARTS_DTLS\": \"●주재료\\n실곤약 440g, 검은콩 70g, 볶은 흑임자 6g(1작은술), 호두 6g(1알)\\n●장식\\n오이 20g(1/8개), 토마토 50g(1/2개), 달걀 50g(1개)\",\n" +
            "        \"manual_IMG01\": \"http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00113_1.png\",\n" +
            "        \"manual06\": \"\",\n" +
            "        \"info_FAT\": \"2.4\",\n" +
            "        \"manual04\": \"\",\n" +
            "        \"hash_TAG\": \"실곤약\",\n" +
            "        \"manual_IMG13\": \"\",\n" +
            "        \"manual11\": \"\",\n" +
            "        \"manual14\": \"\",\n" +
            "        \"manual08\": \"\",\n" +
            "        \"manual_IMG07\": \"\",\n" +
            "        \"manual_IMG09\": \"\",\n" +
            "        \"manual07\": \"\",\n" +
            "        \"manual_IMG10\": \"\",\n" +
            "        \"manual_IMG08\": \"\",\n" +
            "        \"manual09\": \"\",\n" +
            "        \"manual_IMG06\": \"\",\n" +
            "        \"manual10\": \"\",\n" +
            "        \"manual_IMG12\": \"\",\n" +
            "        \"manual_IMG11\": \"\",\n" +
            "        \"manual13\": \"\",\n" +
            "        \"manual12\": \"\",\n" +
            "        \"manual17\": \"\",\n" +
            "        \"manual_IMG15\": \"\",\n" +
            "        \"manual_IMG18\": \"\",\n" +
            "        \"manual19\": \"\",\n" +
            "        \"manual_IMG14\": \"\",\n" +
            "        \"manual20\": \"\",\n" +
            "        \"rcp_NA_TIP\": \"검은콩은 안토시아닌 색소를 많이 함유하고 있어 시력회복과 항암작용에 좋다. 나트륨 함량이 높은 소면을 대신하여 실곤약으로 면을 대체함으로써 나트륨과 칼로리의 섭취량을 줄이고, 콩국물에 흑임자와 호두를 넣어 짠맛 대신 고소한 맛과 향을 강조한다.\",\n" +
            "        \"manual_IMG17\": \"\",\n" +
            "        \"manual_IMG20\": \"\",\n" +
            "        \"manual15\": \"\",\n" +
            "        \"manual_IMG16\": \"\",\n" +
            "        \"manual18\": \"\",\n" +
            "        \"manual_IMG19\": \"\",\n" +
            "        \"manual16\": \"\"\n" +
            "    }";

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

        context = this;

        acButton_home = findViewById(R.id.appCompatButton_main_home);
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

        JSONParseManager jsonParseManager = new JSONParseManager(jsonString);
        jsonParseManager.splitJSON();

        RecipeOrder[] recipeOrder = jsonParseManager.getObject();
        ArrayList<Recipe> recipeDataSet = new ArrayList<>();
        for(int i = 0; i < recipeOrder.length; i++) {
            ImageView iv = (ImageView)findViewById(R.id.imageView_main_itemlist);
            //iv.setImageURI(Uri.parse(recipeOrder[i].getAtt_FILE_NO_MAIN()));
            //Glide.with(this).load(recipeOrder[i].getAtt_FILE_NO_MAIN()).into(iv);

            String img = recipeOrder[i].getAtt_FILE_NO_MAIN();
            String title = recipeOrder[i].getRcp_NM();
            String genre = recipeOrder[i].getRcp_PAT2();
            String amount = recipeOrder[i].getInfo_WGT();
            String time = "60";
            String difficulty = "1";
            String ingredient = recipeOrder[i].getRcp_PARTS_DTLS();
            Log.e("MYLOG:", (img + " " + title + " " + genre + " " + amount + " " + time + " " + difficulty + " " + ingredient));

            recipeDataSet.add(new Recipe(img, title, genre, amount, time, difficulty, ingredient));
        }

        RecipeViewAdapter recipeViewAdapter = new RecipeViewAdapter(recipeDataSet);
        recyclerView.setAdapter(recipeViewAdapter);
    }
}