package com.example.yobi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.Manifest;
import android.app.Activity;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import android.speech.*;
import android.widget.Toast;

public class Activity_recipe_detail_order extends AppCompatActivity implements TextToSpeech.OnInitListener {

    // 요청 코드
    private static final int REQUEST_CODE_STT = 1;
    // TextToSpeech 객체 생성
    private TextToSpeech textToSpeech;
    // SpeechRecognizer 객체 생성
    private SpeechRecognizer speechRecognizer;
    private Handler handler = new Handler(Looper.getMainLooper());

    // Data 객체
    ArrayList<RecipeOrderDetail> recipeOrderDetailArrayList;
    int order_num;

    // 컴포넌트 객체
    AppCompatButton nextButton;
    Spinner difficulty, amount;
    LinearLayout start;
    ImageView imageView, image_ingredient, imageMic;
    TextView step, description, description_sub, description_ingredient, mic01, mic02;
    Button backButton;

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
        backButton = (Button) findViewById(R.id.button_login_normal_backspace_01);

        // 비가시 처리
        amount.setVisibility(View.GONE);
        difficulty.setVisibility(View.GONE);
        description_sub.setVisibility(View.GONE);
        image_ingredient.setVisibility(View.GONE);
        description_ingredient.setVisibility(View.GONE);
        imageMic.setVisibility(View.GONE);
        mic01.setVisibility(View.GONE);
        mic02.setVisibility(View.GONE);
        start.setVisibility(View.GONE);

        // 뒤로 버튼 이벤트
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --order_num;
                if (order_num < 1 || order_num > recipeOrderDetailArrayList.size()) {
                    order_num=1;
                    textToSpeech.speak("레시피 안내를 종료합니다.", TextToSpeech.QUEUE_FLUSH, null, null);
                    Toast.makeText(Activity_recipe_detail_order.this, "레시피 안내를 종료합니다.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Intent intent = new Intent(Activity_recipe_detail_order.this, Activity_recipe_detail_order.class);
                    intent.putExtra("seq_num", order_num);
                    intent.putExtra("dataSet", recipeOrderDetailArrayList);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // 다음 버튼 이벤트
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++order_num;
                if (order_num < 1 || order_num > recipeOrderDetailArrayList.size()) {
                    order_num=1;
                    finish();
                    textToSpeech.speak("레시피 안내를 종료합니다.", TextToSpeech.QUEUE_FLUSH, null, null);
                    Toast.makeText(Activity_recipe_detail_order.this, "레시피 안내를 종료합니다.", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Activity_recipe_detail_order.this, Activity_recipe_detail_order.class);
                    intent.putExtra("seq_num", order_num);
                    intent.putExtra("dataSet", recipeOrderDetailArrayList);
                    startActivity(intent);
                    finish();
                }
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

        // TextToSpeech 초기화
        textToSpeech = new TextToSpeech(this, this);

        // STT 초기화 및 시작
        initSTT();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // TTS 엔진 초기화 성공
            int result = textToSpeech.setLanguage(Locale.KOREAN);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "TTS: 언어가 지원되지 않습니다.", Toast.LENGTH_SHORT).show();
                Log.e("TTS", "Language is not supported or missing data");
            } else {
                // TTS 초기화가 완료된 후에만 speak() 호출
                speak();
            }
        } else {
            Toast.makeText(this, "TTS 초기화 실패", Toast.LENGTH_SHORT).show();
            Log.e("TTS", "Initialization failed");
        }
    }

    private void speak() {
        String text = description.getText().toString();
        if (!text.isEmpty()) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            Log.d("TTS", "Speaking: " + text);
        } else {
            Toast.makeText(this, "텍스트 인식 실패", Toast.LENGTH_SHORT).show();
            Log.e("TTS", "Text recognition failed");
        }
    }

    private void initSTT() {
        // STT 초기화
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {
                    // 준비 완료
                }

                @Override
                public void onBeginningOfSpeech() {
                    // 음성 입력 시작
                }

                @Override
                public void onRmsChanged(float rmsdB) {
                    // 입력 소리 변화
                }

                @Override
                public void onBufferReceived(byte[] buffer) {
                    // 버퍼 데이터 수신
                }

                @Override
                public void onEndOfSpeech() {
                    // 음성 입력 종료
                    // 음성 입력 종료 시 다시 시작
                    handler.postDelayed(() -> startListening(), 1000); // 1초 지연
                }

                @Override
                public void onError(int error) {
                    //Toast.makeText(Activity_recipe_detail_order.this, "에러 발생: " + error, Toast.LENGTH_SHORT).show();
//                    Log.e("STT", "Error: " + error);
                    handler.postDelayed(() -> startListening(), 1000); // 1초 지연
                }

                @Override
                public void onResults(Bundle results) {
                    if (results != null) {
                        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        if (matches != null && !matches.isEmpty()) {
                            String recognizedText = matches.get(0);
                            //description.setText(recognizedText);
                            Log.d("STT", "Recognized text: " + recognizedText);
                            // 음성 분류
                            switch (recognizedText) {
                                case "다음": case "다음거": case "다음으로": case "다음 거": case "넘겨": case "넘겨줘": case "넘겨 줘": case "다음 단계": case "끝났어":
                                    // 다음 단계로
                                    ++order_num;
                                    if (order_num < 1 || order_num > recipeOrderDetailArrayList.size()) {
                                        order_num=1;
                                        finish();
                                        textToSpeech.speak("레시피 안내를 종료합니다.", TextToSpeech.QUEUE_FLUSH, null, null);
                                        Toast.makeText(Activity_recipe_detail_order.this, "레시피 안내를 종료합니다.", Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(Activity_recipe_detail_order.this, Activity_recipe_detail_order.class);
                                        intent.putExtra("seq_num", order_num);
                                        intent.putExtra("dataSet", recipeOrderDetailArrayList);
                                        startActivity(intent);
                                        finish();
                                    }
                                    break;
                                case "정지": case "일시정지": case "잠깐": case "잠깐만": case "잠시만": case "기다려": case "기다려 줘": case "멈춰": case "멈춰봐":
                                    // tts 중간에 끊기, 타이머 정지
                                    break;
                                case "타이머": case "시간": case "시간 설정": case "타이머 설정":
                                    // 타이머 설정
                                    break;
                                case "다시": case "다시 읽어": case "다시 읽어 줘": case "뭐라고": case "못들었어": case "다시 말해줘":
                                    // tts 다시시작
                                    speak();
                                    break;
                                case "이전": case "이전으로": case "뒤로": case "뒤로 가줘": case "앞으로": case "앞으로 가줘": case "이전 단계":
                                    // 이전 단계로
                                    --order_num;
                                    if (order_num < 1 || order_num > recipeOrderDetailArrayList.size()) {
                                        order_num=1;
                                        textToSpeech.speak("레시피 안내를 종료합니다.", TextToSpeech.QUEUE_FLUSH, null, null);
                                        Toast.makeText(Activity_recipe_detail_order.this, "레시피 안내를 종료합니다.", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Intent intent_b = new Intent(Activity_recipe_detail_order.this, Activity_recipe_detail_order.class);
                                        intent_b.putExtra("seq_num", order_num);
                                        intent_b.putExtra("dataSet", recipeOrderDetailArrayList);
                                        startActivity(intent_b);
                                        finish();
                                    }
                                    break;
                                case "그만": case "그만하기": case "그만할래": case "끝내줘": case "꺼줘": case "종료": case "종료해 줘": case "종료해":
                                    // 조리안내 종료
                                    order_num=1;
                                    textToSpeech.speak("레시피 안내를 종료합니다.", TextToSpeech.QUEUE_FLUSH, null, null);
                                    Toast.makeText(Activity_recipe_detail_order.this, "레시피 안내를 종료합니다.", Toast.LENGTH_LONG).show();
                                    finish();
                            }
                        } else {
                            Log.e("STT", "No speech results");
                            handler.postDelayed(() -> startListening(), 1000); // 1초 지연
                        }
                    } else {
                        Log.e("STT", "Results bundle is null");
                        handler.postDelayed(() -> startListening(), 1000); // 1초 지연
                    }
                    // 결과 처리 후 다시 시작
                    handler.postDelayed(() -> startListening(), 1000); // 1초 지연
                }

                @Override
                public void onPartialResults(Bundle partialResults) {
                    // 부분 결과
                }

                @Override
                public void onEvent(int eventType, Bundle params) {
                    // 이벤트 발생
                }
            });

            // 음성 인식 시작
            handler.postDelayed(() -> startListening(), 1000); // 1초 지연
        } else {
            Toast.makeText(this, "음성 인식을 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
            Log.e("STT", "Speech recognition is not available");
        }
    }

    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.KOREAN);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        speechRecognizer.startListening(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_STT && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && !results.isEmpty()) {
                String recognizedText = results.get(0);
                Toast.makeText(this, "인식된 텍스트: " + recognizedText, Toast.LENGTH_LONG).show();
                if (recognizedText.equals("다음") || recognizedText.equals("다음거") || recognizedText.equals("다음으로") || recognizedText.equals("넘겨") || recognizedText.equals("넘겨줘")) {
                    Intent intent = new Intent(Activity_recipe_detail_order.this, Activity_recipe_detail_order.class);
                    intent.putExtra("seq_num", ++order_num);
                    intent.putExtra("dataSet", recipeOrderDetailArrayList);
                    startActivity(intent);
                }
            }
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

    @Override
    protected void onDestroy() {
        // TextToSpeech 자원 해제
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
