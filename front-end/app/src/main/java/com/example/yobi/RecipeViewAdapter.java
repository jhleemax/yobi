package com.example.yobi;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewAdapter.ViewHolder> {

    private ArrayList<Recipe> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView recipe_thumbnail;
        protected TextView
                recipe_title,
                recipe_genre,
                recipe_amount,
                recipe_time,
                recipe_difficulty,
                recipe_ingredient;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recipe_thumbnail = (ImageView) itemView.findViewById(R.id.imageView_recipe_itemlist);
            this.recipe_title = (TextView) itemView.findViewById(R.id.textView_recipe_itemlist_01);
            this.recipe_genre = (TextView) itemView.findViewById(R.id.textView_recipe_itemlist_02);
            this.recipe_amount = (TextView) itemView.findViewById(R.id.textView_recipe_itemlist_03);
            this.recipe_time = (TextView) itemView.findViewById(R.id.textView_recipe_itemlist_04);
            this.recipe_difficulty = (TextView) itemView.findViewById(R.id.textView_recipe_itemlist_05);
            this.recipe_ingredient = (TextView) itemView.findViewById(R.id.textView_recipe_itemlist_06);
        }

        public ImageView getRecipe_thumbnail() {
            return recipe_thumbnail;
        }

        public TextView getRecipe_title() { return recipe_title; }

        public TextView getRecipe_genre() {
            return recipe_genre;
        }

        public TextView getRecipe_amount() {
            return recipe_amount;
        }

        public TextView getRecipe_time() {
            return recipe_time;
        }

        public TextView getRecipe_difficulty() {
            return recipe_difficulty;
        }

        public TextView getRecipe_ingredient() {
            return recipe_ingredient;
        }

        void onBind(Recipe item) {
            //recipe_thumbnail.setImageResource(item.getThumbnail());
            //Glide.with(Activity_recipe.context).load(item.getThumbnail()).into(recipe_thumbnail);
            Thread th1 = new Thread(() -> {
                Log.e("RecipeViewAdapter.onBind : ", item.getThumbnail());;
                recipe_thumbnail.setImageBitmap(getBitmapFromURL(item.getThumbnail()));
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

        RecipeViewAdapter.ViewHolder viewHolder = new RecipeViewAdapter.ViewHolder(view);

        return viewHolder;
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
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
