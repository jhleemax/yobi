package com.example.yobi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class UserRecipeImage {
    String url;

    public UserRecipeImage() {}
    public UserRecipeImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
public class ImageRecyclerViewAdaptor extends RecyclerView.Adapter<ImageRecyclerViewAdaptor.ViewHolder> {

    private ArrayList<UserRecipeImage> dataSet;
    private ImageRecyclerViewAdaptor.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(ImageRecyclerViewAdaptor.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final FloatingActionButton delete;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View view, ImageRecyclerViewAdaptor.OnItemClickListener listener) {
            super(view);

            // 뷰 초기화
            image = view.findViewById(R.id.imageView_recipe_write_addition);
            delete = view.findViewById(R.id.floatingActionButton_delete);

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

            // image 버튼에 클릭 리스너 부착
            image.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });

            // delete 버튼에 클릭 리스너 부착
            delete.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });
        }

        public void onBind(UserRecipeImage item) {
            image.setPadding(0, 0, 0, 0);

            Uri uri = Uri.parse(item.getUrl());
            image.setImageURI(uri);

            if(image.getDrawable() == null) {
                new Thread(() -> {
                    Bitmap thumbnail = getBitmapFromURL(item.getUrl());
                    image.post(() -> image.setImageBitmap(thumbnail));
                }).start();
            }
            /*
            new Thread(() -> {
                Bitmap thumbnail = getBitmapFromURL(item.getUrl());
                image.post(() -> image.setImageBitmap(thumbnail));
            }).start();
             */
        }
    }

    public ImageRecyclerViewAdaptor(ArrayList<UserRecipeImage> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ImageRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_write_image_itemlist, parent, false);
        return new ImageRecyclerViewAdaptor.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecyclerViewAdaptor.ViewHolder holder, int position) {
        holder.onBind(dataSet.get(position));

        //((ViewHolder) holder).recipe_image.getLayoutParams().height = 400;
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