// RecipeViewAdapter.java
package com.example.yobi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewAdapter.ViewHolder> {

    private ArrayList<Recipe> dataSet;
    private OnItemClickListener listener;

    // 아이템 클릭 리스너 인터페이스 정의
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // 리스너 설정 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipe_title;
        private final TextView recipe_genre;
        private final TextView recipe_amount;
        private final TextView recipe_time;
        private final TextView recipe_difficulty;
        private final TextView recipe_ingredient;
        private final ImageView imageView;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View view, OnItemClickListener listener) {
            super(view);

            // 뷰 초기화
            recipe_title = view.findViewById(R.id.textView_recipe_itemlist_01);
            recipe_genre = view.findViewById(R.id.textView_recipe_itemlist_02);
            recipe_amount = view.findViewById(R.id.textView_recipe_itemlist_03);
            recipe_time = view.findViewById(R.id.textView_recipe_itemlist_04);
            recipe_difficulty = view.findViewById(R.id.textView_recipe_itemlist_05);
            recipe_ingredient = view.findViewById(R.id.textView_recipe_itemlist_06);
            imageView = view.findViewById(R.id.imageView_recipe_itemlist);

            // itemView에 클릭 리스너 부착
            view.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });
        }

        public void onBind(Recipe item) {
            Thread th1 = new Thread(() -> {
                Bitmap bitmap = getBitmapFromURL(item.getThumbnail());
                imageView.post(() -> imageView.setImageBitmap(bitmap));
            });

            Thread th2 = new Thread(() -> {
                recipe_title.setText(item.getTitle());
                recipe_genre.setText(item.getGenre());
                recipe_amount.setText(item.getAmount());
                recipe_time.setText(item.getTime());
                recipe_difficulty.setText(item.getDifficulty());
                recipe_ingredient.setText(item.getIngredient());
            });

            try {
                th1.start();
                th1.join();

                th2.start();
                th2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public RecipeViewAdapter(ArrayList<Recipe> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecipeViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_itemlist, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewAdapter.ViewHolder holder, int position) {
        holder.onBind(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
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
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }
}
