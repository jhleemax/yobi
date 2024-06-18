package com.example.yobi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.graphics.PathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Activity_recipe_write extends AppCompatActivity {

    // 데이터
    String jsonString;
    Uri imageUri;
    int posi; // 이미지 전용 포지션 변수
    ArrayList<UserRecipeImage> userRecipeImages;
    ImageRecyclerViewAdaptor imageRecyclerViewAdaptor;

    // 컴포넌트
    AppCompatButton plusImage, submit, plusOrder, plusIngredient;
    EditText title, description;
    TextView textView_description;
    Spinner category;
    RecyclerView order, ingredient;
    RecyclerView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_write);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 컴포넌트 초기화
        title = findViewById(R.id.editText_recipe_write_title);
        category = findViewById(R.id.spinner_recipe_write_category);
        description = findViewById(R.id.editText_recipe_write_description);
        textView_description = findViewById(R.id.textView_recipe_write_description);
        order = findViewById(R.id.listView_recipe_write_order);
        plusOrder = findViewById(R.id.appCompatButton_recipe_write_orderAddition);
        ingredient = findViewById(R.id.listView_recipe_write_ingredient);
        image = findViewById(R.id.listView_recipe_write_image);
        plusImage = findViewById(R.id.appCompatButton_recipe_write_imageAddition);
        submit = findViewById(R.id.appCompatButton_recipe_write_submit);
        plusIngredient = findViewById(R.id.appCompatButton_recipe_write_ingredientAddition);

        // Spinner 초기화
        String[] array = getResources().getStringArray(R.array.category);

        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.spinner_category_item, array);
        adapter.setDropDownViewResource(R.layout.spinner_category_item);
        category.setAdapter(adapter);

        // 컴포넌트 비활성화
        description.setVisibility(View.GONE);
        textView_description.setVisibility(View.GONE);

        // recipeNum 받아오기
        Intent intent = getIntent();
        String recipeNum = intent.getStringExtra("recipeNum");

        // 레시피 수정일 경우
        if(recipeNum != null) {
            HttpConnectionManager httpConnectionManager = new HttpConnectionManager(
                    "http://10.0.2.2:8080/recipe/read/" + recipeNum,
                    "GET"
            );

            Thread getResponseThread = new Thread(() -> {
                jsonString = httpConnectionManager.getResponse();
            });

            Thread jsonParsingThread = new Thread(() -> {
                JSONParseManager jsonParseManager = new JSONParseManager(jsonString);
                jsonParseManager.splitJSON();

                UserRecipe userRecipe = jsonParseManager.getObjectbyUserRecipe()[0];

                // 제목 설정
                title.setText(userRecipe.getFoodName());

                // 카테고리 설정
                int pos = 0;
                for(int i = 0; i < array.length; i++) {
                    if(array[i].equals(userRecipe.getRecipeCategory())) {
                        pos = i;
                    }
                }
                category.setSelection(pos);

                // 순서 설정
                ArrayList<UserRecipeOrder> userRecipeOrders = new ArrayList<>();
                //Log.e("manual01", userRecipe.getRecipeManual01());
                //Log.e("manual02", userRecipe.getRecipeManual02());
                if(userRecipe.getRecipeManual01() == null || !(userRecipe.getRecipeManual01().isEmpty())) {
                    Log.e("Activity_recipe_write","1st if Run");
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual01(), "1"));
                }
                if(userRecipe.getRecipeManual02() != null && !(userRecipe.getRecipeManual02().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual02(), "2"));
                }
                if(userRecipe.getRecipeManual03() != null && !(userRecipe.getRecipeManual03().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual03(), "3"));
                }
                if(userRecipe.getRecipeManual04() != null && !(userRecipe.getRecipeManual04().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual04(), "4"));
                }
                if(userRecipe.getRecipeManual05() != null && !(userRecipe.getRecipeManual05().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual05(), "5"));
                }
                if(userRecipe.getRecipeManual06() != null && !(userRecipe.getRecipeManual06().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual06(), "6"));
                }
                if(userRecipe.getRecipeManual07() != null && !(userRecipe.getRecipeManual07().isEmpty())) {
                    Log.e("Activity_recipe_write","7th if Run");
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual07(), "7"));
                }
                if(userRecipe.getRecipeManual08() != null && !(userRecipe.getRecipeManual08().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual08(), "8"));
                }
                if(userRecipe.getRecipeManual09() != null && !(userRecipe.getRecipeManual09().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual09(), "9"));
                }
                if(userRecipe.getRecipeManual10() != null && !(userRecipe.getRecipeManual10().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual10(), "10"));
                }
                if(userRecipe.getRecipeManual11() != null && !(userRecipe.getRecipeManual11().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual11(), "11"));
                }
                if(userRecipe.getRecipeManual12() != null && !(userRecipe.getRecipeManual12().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual12(), "12"));
                }
                if(userRecipe.getRecipeManual13() != null && !(userRecipe.getRecipeManual13().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual13(), "13"));
                }
                if(userRecipe.getRecipeManual14() != null && !(userRecipe.getRecipeManual14().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual14(), "14"));
                }
                if(userRecipe.getRecipeManual15() != null && !(userRecipe.getRecipeManual15().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual15(), "15"));
                }
                if(userRecipe.getRecipeManual16() != null && !(userRecipe.getRecipeManual16().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual16(), "16"));
                }
                if(userRecipe.getRecipeManual17() != null && !(userRecipe.getRecipeManual17().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual17(), "17"));
                }
                if(userRecipe.getRecipeManual18() != null && !(userRecipe.getRecipeManual18().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual18(), "18"));
                }
                if(userRecipe.getRecipeManual19() != null && !(userRecipe.getRecipeManual19().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual19(), "19"));
                }
                if(userRecipe.getRecipeManual20() != null && !(userRecipe.getRecipeManual20().isEmpty())) {
                    userRecipeOrders.add(new UserRecipeOrder(userRecipe.getRecipeManual20(), "20"));
                }

                OrderRecyclerViewAdaptor orderRecyclerViewAdaptor = new OrderRecyclerViewAdaptor(userRecipeOrders);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                order.setLayoutManager(linearLayoutManager);
                order.setAdapter(orderRecyclerViewAdaptor);

                // 아이템 클릭 리스너 설정
                // 순서 삭제
                orderRecyclerViewAdaptor.setOnItemClickListener(new OrderRecyclerViewAdaptor.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (userRecipeOrders.size() > 1) {
                            userRecipeOrders.remove(position);

                            Log.e("userRecipeOrder Size", Integer.toString(userRecipeOrders.size()));
                            orderRecyclerViewAdaptor.notifyItemRemoved(position);
                        }
                    }
                });

                // 순서 추가
                plusOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(userRecipeOrders.size() < 20) {
                            userRecipeOrders.add(new UserRecipeOrder("", Integer.toString(userRecipeOrders.size()+1)));

                            Log.e("userRecipeOrder Size", Integer.toString(userRecipeOrders.size()));
                            orderRecyclerViewAdaptor.notifyItemInserted(userRecipeOrders.size());
                        }
                    }
                });
                // 순서 변경
                orderRecyclerViewAdaptor.setOnTextChangeListener(new OrderRecyclerViewAdaptor.OnTextChangeListener() {
                    @Override
                    public void onTextChanged(int position, String text) {
                        userRecipeOrders.set(position, new UserRecipeOrder(text, Integer.toString(position)));

                        //orderRecyclerViewAdaptor.notifyItemChanged(position);
                        Log.e("순서 변경", userRecipeOrders.get(position).contents);
                    }
                });

                // 재료 설정
                ArrayList<UserRecipeIngredient> userRecipeIngredients = new ArrayList<>();
                if(userRecipe.getIngredient() != null && !(userRecipe.getIngredient().isEmpty())) {
                    String data = userRecipe.getMaterial();
                    String[] splitbyComma = data.split(",");
                    for(String s : splitbyComma) {
                        userRecipeIngredients.add(new UserRecipeIngredient(s.split("-")[0], s.split("-")[1]));
                    }
                }

                IngredientRecyclerViewAdaptor ingredientRecyclerViewAdaptor = new IngredientRecyclerViewAdaptor(userRecipeIngredients);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager((Context) this);
                linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                ingredient.setLayoutManager(linearLayoutManager1);
                ingredient.setAdapter(ingredientRecyclerViewAdaptor);

                // 재료 데이터 변경 시 이벤트
                ingredientRecyclerViewAdaptor.setOnTextChangeListener(new IngredientRecyclerViewAdaptor.OnTextChangeListener() {
                    @Override
                    public void onTextChanged(int position, String text, View view) {
                        // 재료 변경 시
                        if(view.getId() == R.id.editText_recipe_write_ingredient_itemlist_01) {
                            String count = userRecipeIngredients.get(position).getCount();
                            userRecipeIngredients.set(position, new UserRecipeIngredient(text, count));

                            //ingredientRecyclerViewAdaptor.notifyDataSetChanged();
                            Log.e("DataSet", userRecipeIngredients.get(position).getIngredient() + "-" + userRecipeIngredients.get(position).count);
                        }
                        // 개수 변경 시
                        if(view.getId() == R.id.editText_recipe_write_ingredient_itemlist_02) {
                            String ingre = userRecipeIngredients.get(position).getIngredient();
                            userRecipeIngredients.set(position, new UserRecipeIngredient(ingre, text));

                            //ingredientRecyclerViewAdaptor.notifyDataSetChanged();
                            Log.e("DataSet", userRecipeIngredients.get(position).getIngredient() + "-" + userRecipeIngredients.get(position).count);
                        }
                    }
                });

                // 재료 추가 이벤트
                plusIngredient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userRecipeIngredients.add(new UserRecipeIngredient("",""));

                        ingredientRecyclerViewAdaptor.notifyItemInserted(userRecipeIngredients.size());
                    }
                });

                // 재료 삭제 이벤트
                ingredientRecyclerViewAdaptor.setOnItemClickListener(new IngredientRecyclerViewAdaptor.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if(view.getId() == R.id.textView_recipe_write_ingredient_itemlist_03) {
                            if(userRecipeIngredients.size() > 1) {
                                userRecipeIngredients.remove(position);

                                ingredientRecyclerViewAdaptor.notifyItemRemoved(position);
                            }
                        }
                    }
                });

                // 사진 설정
                userRecipeImages = new ArrayList<>();
                if(userRecipe.getRecipeImage01() != null && !(userRecipe.getRecipeImage01().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage01()));
                }
                if(userRecipe.getRecipeImage02() != null && !(userRecipe.getRecipeImage02().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage02()));
                }
                if(userRecipe.getRecipeImage03() != null && !(userRecipe.getRecipeImage03().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage03()));
                }
                if(userRecipe.getRecipeImage04() != null && !(userRecipe.getRecipeImage04().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage04()));
                }
                if(userRecipe.getRecipeImage05() != null && !(userRecipe.getRecipeImage05().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage05()));
                }
                if(userRecipe.getRecipeImage06() != null && !(userRecipe.getRecipeImage06().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage06()));
                }
                if(userRecipe.getRecipeImage07() != null && !(userRecipe.getRecipeImage07().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage07()));
                }
                if(userRecipe.getRecipeImage08() != null && !(userRecipe.getRecipeImage08().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage08()));
                }
                if(userRecipe.getRecipeImage09() != null && !(userRecipe.getRecipeImage09().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage09()));
                }
                if(userRecipe.getRecipeImage10() != null && !(userRecipe.getRecipeImage10().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage10()));
                }
                if(userRecipe.getRecipeImage11() != null && !(userRecipe.getRecipeImage11().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage11()));
                }
                if(userRecipe.getRecipeImage12() != null && !(userRecipe.getRecipeImage12().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage12()));
                }
                if(userRecipe.getRecipeImage13() != null && !(userRecipe.getRecipeImage13().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage13()));
                }
                if(userRecipe.getRecipeImage14() != null && !(userRecipe.getRecipeImage14().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage14()));
                }
                if(userRecipe.getRecipeImage15() != null && !(userRecipe.getRecipeImage15().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage15()));
                }
                if(userRecipe.getRecipeImage16() != null && !(userRecipe.getRecipeImage16().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage16()));
                }
                if(userRecipe.getRecipeImage17() != null && !(userRecipe.getRecipeImage17().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage17()));
                }
                if(userRecipe.getRecipeImage18() != null && !(userRecipe.getRecipeImage18().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage18()));
                }
                if(userRecipe.getRecipeImage19() != null && !(userRecipe.getRecipeImage19().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage19()));
                }
                if(userRecipe.getRecipeImage20() != null && !(userRecipe.getRecipeImage20().isEmpty())) {
                    userRecipeImages.add(new UserRecipeImage(userRecipe.getRecipeImage20()));
                }
                imageRecyclerViewAdaptor = new ImageRecyclerViewAdaptor(userRecipeImages);
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager((Context) this);
                linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                image.setLayoutManager(linearLayoutManager2);
                image.setAdapter(imageRecyclerViewAdaptor);

                // 사진 추가 버튼 이벤트 리스너
                plusImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(userRecipeImages.size() < 20) {
                            userRecipeImages.add(new UserRecipeImage(""));

                            imageRecyclerViewAdaptor.notifyItemInserted(userRecipeImages.size());
                        }
                    }
                });
                // 사진 삭제 버튼 이벤트 리스너
                imageRecyclerViewAdaptor.setOnItemClickListener(new ImageRecyclerViewAdaptor.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // 삭제 버튼일 경우
                        if(view.getId() == R.id.floatingActionButton_delete) {
                            userRecipeImages.remove(position);

                            imageRecyclerViewAdaptor.notifyItemRemoved(position);
                        }
                        // 이미지 클릭일 경우
                        if(view.getId() == R.id.imageView_recipe_write_addition) {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);

                            startActivityResult.launch(intent);
                            posi = position;
                        }
                    }
                });

                // 저장 버튼
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HttpConnectionManager httpConnectionManager1 = new HttpConnectionManager(
                                "http://10.0.2.2:8080/recipe/update/" + recipeNum,
                                "PATCH"
                        );
                        // 카테고리, 음식명 추가
                        String json =
                                "{" +
                                    "\"recipeCategory\" : \"" + array[category.getSelectedItemPosition()] + "\"," +
                                    "\"foodName\" : \"" + title.getText().toString() + "\"";

                        // 재료 추가
                        StringBuilder ingredients = new StringBuilder();
                        for(UserRecipeIngredient ingre : userRecipeIngredients) {
                            ingredients.append(ingre.getIngredient()).append("-").append(ingre.getCount()).append(",");
                        }
                        ingredients.substring(0, ingredients.length()-1);

                        json += ",\"material\" : \"" + ingredients + "\"";

                        // userid 추가
                        json += ",\"userId\" : \"" + "test" + "\"";

                        // 레시피 조리 순서 추가
                        for(int i = 0; i < userRecipeOrders.size(); i++) {
                            if(i < 9)
                                json += ",\"recipeManual0" + Integer.toString(i+1) + "\" : \"" + userRecipeOrders.get(i).getContents() + "\"";
                            else
                                json += ",\"recipeManual" + Integer.toString(i+1) + "\" : \"" + userRecipeOrders.get(i).getContents() + "\"";
                        }

                        // 레시피 사진 추가
                        for(int i = 0; i < userRecipeImages.size(); i++) {
                            if(i < 9)
                                json += ",\"recipeImage0" + Integer.toString(i+1) + "\" : \"" + userRecipeImages.get(i).getUrl() + "\"";
                            else
                                json += ",\"recipeImage" + Integer.toString(i+1) + "\" : \"" + userRecipeImages.get(i).getUrl() + "\"";
                        }

                        json += "}";

                        String finalJson = json;
                        new Thread(() -> {
                            String result = null;
                            try {
                                result = httpConnectionManager1.doPostbyJSON(finalJson);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
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
        } else { // 레시피 작성일 경우
            // 순서 설정
            ArrayList<UserRecipeOrder> userRecipeOrders = new ArrayList<>();
            //Log.e("manual01", userRecipe.getRecipeManual01());
            //Log.e("manual02", userRecipe.getRecipeManual02());

            OrderRecyclerViewAdaptor orderRecyclerViewAdaptor = new OrderRecyclerViewAdaptor(userRecipeOrders);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            order.setLayoutManager(linearLayoutManager);
            order.setAdapter(orderRecyclerViewAdaptor);

            // 아이템 클릭 리스너 설정
            // 순서 삭제
            orderRecyclerViewAdaptor.setOnItemClickListener(new OrderRecyclerViewAdaptor.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (userRecipeOrders.size() > 1) {
                        userRecipeOrders.remove(position);

                        Log.e("userRecipeOrder Size", Integer.toString(userRecipeOrders.size()));
                        orderRecyclerViewAdaptor.notifyItemRemoved(position);
                    }
                }
            });

            // 순서 추가
            plusOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(userRecipeOrders.size() < 20) {
                        userRecipeOrders.add(new UserRecipeOrder("", Integer.toString(userRecipeOrders.size()+1)));

                        Log.e("userRecipeOrder Size", Integer.toString(userRecipeOrders.size()));
                        orderRecyclerViewAdaptor.notifyItemInserted(userRecipeOrders.size());
                    }
                }
            });
            // 순서 변경
            orderRecyclerViewAdaptor.setOnTextChangeListener(new OrderRecyclerViewAdaptor.OnTextChangeListener() {
                @Override
                public void onTextChanged(int position, String text) {
                    userRecipeOrders.set(position, new UserRecipeOrder(text, Integer.toString(position)));

                    //orderRecyclerViewAdaptor.notifyItemChanged(position);
                    Log.e("순서 변경", userRecipeOrders.get(position).contents);
                }
            });

            // 재료 설정
            ArrayList<UserRecipeIngredient> userRecipeIngredients = new ArrayList<>();

            IngredientRecyclerViewAdaptor ingredientRecyclerViewAdaptor = new IngredientRecyclerViewAdaptor(userRecipeIngredients);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager((Context) this);
            linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
            ingredient.setLayoutManager(linearLayoutManager1);
            ingredient.setAdapter(ingredientRecyclerViewAdaptor);

            // 재료 데이터 변경 시 이벤트
            ingredientRecyclerViewAdaptor.setOnTextChangeListener(new IngredientRecyclerViewAdaptor.OnTextChangeListener() {
                @Override
                public void onTextChanged(int position, String text, View view) {
                    // 재료 변경 시
                    if(view.getId() == R.id.editText_recipe_write_ingredient_itemlist_01) {
                        String count = userRecipeIngredients.get(position).getCount();
                        userRecipeIngredients.set(position, new UserRecipeIngredient(text, count));

                        //ingredientRecyclerViewAdaptor.notifyDataSetChanged();
                        Log.e("DataSet", userRecipeIngredients.get(position).getIngredient() + "-" + userRecipeIngredients.get(position).count);
                    }
                    // 개수 변경 시
                    if(view.getId() == R.id.editText_recipe_write_ingredient_itemlist_02) {
                        String ingre = userRecipeIngredients.get(position).getIngredient();
                        userRecipeIngredients.set(position, new UserRecipeIngredient(ingre, text));

                        //ingredientRecyclerViewAdaptor.notifyDataSetChanged();
                        Log.e("DataSet", userRecipeIngredients.get(position).getIngredient() + "-" + userRecipeIngredients.get(position).count);
                    }
                }
            });

            // 재료 추가 이벤트
            plusIngredient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userRecipeIngredients.add(new UserRecipeIngredient("",""));

                    ingredientRecyclerViewAdaptor.notifyItemInserted(userRecipeIngredients.size());
                }
            });

            // 재료 삭제 이벤트
            ingredientRecyclerViewAdaptor.setOnItemClickListener(new IngredientRecyclerViewAdaptor.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(view.getId() == R.id.textView_recipe_write_ingredient_itemlist_03) {
                        if(userRecipeIngredients.size() > 1) {
                            userRecipeIngredients.remove(position);

                            ingredientRecyclerViewAdaptor.notifyItemRemoved(position);
                        }
                    }
                }
            });

            // 사진 설정
            userRecipeImages = new ArrayList<>();

            imageRecyclerViewAdaptor = new ImageRecyclerViewAdaptor(userRecipeImages);
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager((Context) this);
            linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            image.setLayoutManager(linearLayoutManager2);
            image.setAdapter(imageRecyclerViewAdaptor);

            // 사진 추가 버튼 이벤트 리스너
            plusImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(userRecipeImages.size() < 20) {
                        userRecipeImages.add(new UserRecipeImage(""));

                        imageRecyclerViewAdaptor.notifyItemInserted(userRecipeImages.size());
                    }
                }
            });
            // 사진 삭제 버튼 이벤트 리스너
            imageRecyclerViewAdaptor.setOnItemClickListener(new ImageRecyclerViewAdaptor.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    // 삭제 버튼일 경우
                    if(view.getId() == R.id.floatingActionButton_delete) {
                        userRecipeImages.remove(position);

                        imageRecyclerViewAdaptor.notifyItemRemoved(position);
                    }
                    // 이미지 클릭일 경우
                    if(view.getId() == R.id.imageView_recipe_write_addition) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);

                        startActivityResult.launch(intent);
                        posi = position;
                    }
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HttpConnectionManager httpConnectionManager2 = new HttpConnectionManager(
                            "http://10.0.2.2:8080/recipe/write",
                            "POST"
                    );

                    MultipartFormData formData = new MultipartFormData();
                    // 폼데이터 처리 로직
                    try {
                        // userId
                        formData.addString("userId", "test", "UTF-8");

                        // 카테고리
                        formData.addString("recipeCategory", array[category.getSelectedItemPosition()], "UTF-8");

                        // 음식명
                        formData.addString("foodName", title.getText().toString(), "UTF-8");

                        // 재료
                        StringBuilder ingredients = new StringBuilder();
                        for(UserRecipeIngredient ingre : userRecipeIngredients) {
                            ingredients.append(ingre.getIngredient()).append("-").append(ingre.getCount()).append(",");
                        }
                        ingredients.substring(0, ingredients.length()-1);
                        formData.addString("material", ingredients.toString(), "UTF-8");

                        // 레시피 매뉴얼
                        for(int i = 0; i < userRecipeOrders.size(); i++) {
                            if(i < 9)
                                formData.addString("recipeManual0" + Integer.toString(i+1), userRecipeOrders.get(i).getContents(), "UTF-8");
                            else
                                formData.addString("recipeManual" + Integer.toString(i+1), userRecipeOrders.get(i).getContents(), "UTF-8");
                        }

                        // 레시피 사진(미완)
                        formData.addFile("recipeImages", getRealPathFromURI(imageUri));

                        /*
                        for(int i = 0; i < userRecipeImages.size(); i++) {
                            if(i < 9)

                            else

                        }
                         */

                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        httpConnectionManager2.doPostbyFormData(formData);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if ( result.getResultCode() == RESULT_OK && result.getData() != null) {
                imageUri = result.getData().getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    //iv_upload_image.setImageBitmap(bitmap);		//이미지를 띄울 이미지뷰 설정
                    userRecipeImages.set(posi, new UserRecipeImage(imageUri.toString()));

                    imageRecyclerViewAdaptor.notifyItemChanged(posi);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public String getRealPathFromURI(Uri contentUri) {

        String[] proj = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        cursor.moveToNext();
        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
        Uri uri = Uri.fromFile(new File(path));

        cursor.close();
        return path;
    }
}