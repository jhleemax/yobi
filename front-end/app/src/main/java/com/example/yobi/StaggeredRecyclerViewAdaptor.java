package com.example.yobi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaggeredRecyclerViewAdaptor extends RecyclerView.Adapter<StaggeredRecyclerViewAdaptor.ViewHolder> {
    private ArrayList<Board> dataSet;
    private StaggeredRecyclerViewAdaptor.OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(StaggeredRecyclerViewAdaptor.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView recipe_image;
        private final TextView recipe_title;
        private final CircleImageView user_image;
        private final TextView user_id;
        private final TextView likeCount;
        private final ImageView more;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View view, StaggeredRecyclerViewAdaptor.OnItemClickListener listener) {
            super(view);

            // 뷰 초기화
            recipe_image = view.findViewById(R.id.staggered_recipeImage);
            recipe_title = view.findViewById(R.id.staggered_title);
            user_image = view.findViewById(R.id.staggered_profileImage);
            user_id = view.findViewById(R.id.staggered_userid);
            likeCount = view.findViewById(R.id.staggered_likeCount);
            more = view.findViewById(R.id.staggered_more);

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

            // more 버튼 리스너
            more.setOnClickListener(v -> {
                if(listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });
        }

        public void onBind(Board item) {
            // 비활성화

            // ------------------

            Thread th1 = new Thread(() -> {
                //Log.e("onBind item.getImg() : ", item.getImg());
                Bitmap thumbnail = getBitmapFromURL(item.getThumbnail());
                recipe_image.post(() -> recipe_image.setImageBitmap(thumbnail));

                Bitmap profile = getBitmapFromURL(item.getProfileImg());
                user_image.post(() -> user_image.setImageBitmap(profile));
            });

            Thread th2 = new Thread(() -> {
                recipe_title.setText(item.getTitle());
                user_id.setText(item.getUserid());
                likeCount.setText(item.getLikeCount());
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

    public StaggeredRecyclerViewAdaptor(ArrayList<Board> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public StaggeredRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_itemlist, parent, false);
        return new StaggeredRecyclerViewAdaptor.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredRecyclerViewAdaptor.ViewHolder holder, int position) {
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
