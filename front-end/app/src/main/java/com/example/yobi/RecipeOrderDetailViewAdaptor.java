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

public class RecipeOrderDetailViewAdaptor extends RecyclerView.Adapter<RecipeOrderDetailViewAdaptor.ViewHolder> {

    private ArrayList<RecipeOrderDetail> dataSet;
    private RecipeOrderDetailViewAdaptor.OnItemClickListener listener;

    // 아이템 클릭 리스너 인터페이스 정의
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // 리스너 설정 메서드
    public void setOnItemClickListener(RecipeOrderDetailViewAdaptor.OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView order_img;
        private final TextView order_description;
        private final ImageView order_img_sub01;
        private final TextView order_description_sub01;
        private final ImageView order_img_sub02;
        private final TextView order_description_sub02;


        @SuppressLint("WrongViewCast")
        public ViewHolder(View view, RecipeOrderDetailViewAdaptor.OnItemClickListener listener) {
            super(view);

            // 뷰 초기화
            order_img = view.findViewById(R.id.imageView_recipe_detail_order_itemlist);
            order_description = view.findViewById(R.id.textView_recipe_detail_order_itemlist_description);
            order_img_sub01 = view.findViewById(R.id.imageView_recipe_detail_order_itemlist_sub_description_01);
            order_description_sub01 = view.findViewById(R.id.textView_recipe_detail_order_itemlist_sub_description_01);
            order_img_sub02 = view.findViewById(R.id.imageView_recipe_detail_order_itemlist_sub_description_02);
            order_description_sub02 = view.findViewById(R.id.textView_recipe_detail_order_itemlist_sub_description_02);

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

        public void onBind(RecipeOrderDetail item) {
            // sub 설명, 이미지 비활성화
            order_img_sub01.setVisibility(View.GONE);
            order_description_sub01.setVisibility(View.GONE);
            order_img_sub02.setVisibility(View.GONE);
            order_description_sub02.setVisibility(View.GONE);

            Thread th1 = new Thread(() -> {
                //Log.e("onBind item.getImg() : ", item.getImg());
                Bitmap bitmap = getBitmapFromURL(item.getImg());
                order_img.post(() -> order_img.setImageBitmap(bitmap));
            });

            Thread th2 = new Thread(() -> {
                order_description.setText(item.getMainDescription());
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
    }
    public RecipeOrderDetailViewAdaptor(ArrayList<RecipeOrderDetail> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecipeOrderDetailViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_detail_order_itemlist, parent, false);
        return new RecipeOrderDetailViewAdaptor.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeOrderDetailViewAdaptor.ViewHolder holder, int position) {
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