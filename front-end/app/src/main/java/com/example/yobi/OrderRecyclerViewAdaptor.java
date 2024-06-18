package com.example.yobi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class OrderRecyclerViewAdaptor extends RecyclerView.Adapter<OrderRecyclerViewAdaptor.ViewHolder> {

    private ArrayList<UserRecipeOrder> dataSet;
    private OrderRecyclerViewAdaptor.OnItemClickListener listener;
    private OrderRecyclerViewAdaptor.OnTextChangeListener textChangeListener;

    // 아이템 클릭 리스너
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // editText change 이벤트 리스너
    public interface OnTextChangeListener {
        void onTextChanged(int position, String text);
    }

    public void setOnItemClickListener(OrderRecyclerViewAdaptor.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnTextChangeListener(OrderRecyclerViewAdaptor.OnTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final EditText order_content;
        private final TextView step;
        private final ImageView remove;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View view, OrderRecyclerViewAdaptor.OnItemClickListener listener, OrderRecyclerViewAdaptor.OnTextChangeListener textChangeListener) {
            super(view);

            // 뷰 초기화
            order_content = view.findViewById(R.id.editText_recipe_write_order_itemlist);
            step = view.findViewById(R.id.textView_recipe_write_order_itemlist);
            remove = view.findViewById(R.id.imageView_recipe_write_order_itemlist_substract);

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

            // - 버튼에 클릭 리스너 부착
            remove.setOnClickListener(v -> {
                if(listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });
        }

        public void onBind(UserRecipeOrder item, int position) {
            if(item.getContents().isEmpty()) {
                order_content.setText(item.getNum() + "번째 순서를 작성해주세요.");
            } else {
                order_content.setText(item.getContents());
            }
            step.setText("STEP" + item.getNum());

            order_content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(textChangeListener != null) {
                        Log.e("OrderRecyclerViewAdaptor", "onTextChanged run");
                        textChangeListener.onTextChanged(position, s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public OrderRecyclerViewAdaptor(ArrayList<UserRecipeOrder> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public OrderRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_write_order_itemlist, parent, false);
        return new OrderRecyclerViewAdaptor.ViewHolder(view, listener, textChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerViewAdaptor.ViewHolder holder, int position) {
        holder.onBind(dataSet.get(position), position);

        //((ViewHolder) holder).recipe_image.getLayoutParams().height = 400;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
