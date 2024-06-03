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

public class RecipeIngredientViewAdaptor extends RecyclerView.Adapter<RecipeIngredientViewAdaptor.ViewHolder> {

    private ArrayList<RecipeIngredient> dataSet;
    private RecipeIngredientViewAdaptor.OnItemClickListener listener;

    // 아이템 클릭 리스너 인터페이스 정의
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // 리스너 설정 메서드
    public void setOnItemClickListener(RecipeIngredientViewAdaptor.OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView ingredient_name;
        private final TextView ingredient_amount;
        private final TextView ingredient_required;


        @SuppressLint("WrongViewCast")
        public ViewHolder(View view, RecipeIngredientViewAdaptor.OnItemClickListener listener) {
            super(view);

            // 뷰 초기화
            ingredient_name = view.findViewById(R.id.textView_recipe_detail_ingredient_itemlist_name);
            ingredient_amount = view.findViewById(R.id.textView_recipe_detail_ingredient_itemlist_amount);
            ingredient_required = view.findViewById(R.id.textView_recipe_detail_ingredient_itemlist_required);

            // itemView에 클릭 리스너 부착
            /*
            view.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });
             */
        }

        public void onBind(RecipeIngredient item) {
            ingredient_name.setText(item.getIngredient_name());
            ingredient_amount.setText(item.getIngredient_amount());
            ingredient_required.setText(item.getIngredient_required());
        }
    }
    public RecipeIngredientViewAdaptor(ArrayList<RecipeIngredient> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecipeIngredientViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_detail_ingredient_itemlist, parent, false);
        return new RecipeIngredientViewAdaptor.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientViewAdaptor.ViewHolder holder, int position) {
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
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
