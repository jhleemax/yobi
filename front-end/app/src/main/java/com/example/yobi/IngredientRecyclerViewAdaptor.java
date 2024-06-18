package com.example.yobi;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;

public class IngredientRecyclerViewAdaptor extends RecyclerView.Adapter<IngredientRecyclerViewAdaptor.ViewHolder> {

    private ArrayList<UserRecipeIngredient> dataSet;
    private IngredientRecyclerViewAdaptor.OnItemClickListener listener;
    private IngredientRecyclerViewAdaptor.OnTextChangeListener textChangeListener;

    // 아이템 클릭리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // editText 변경 리스너 인터페이스
    public interface OnTextChangeListener {
        void onTextChanged(int position, String text, View view);
    }

    public void setOnItemClickListener(IngredientRecyclerViewAdaptor.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnTextChangeListener(IngredientRecyclerViewAdaptor.OnTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final EditText ingredient;
        private final EditText countE;
        private final TextView plus;
        private final TextView minus;
        private final TextView delete;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View view, IngredientRecyclerViewAdaptor.OnItemClickListener listener, IngredientRecyclerViewAdaptor.OnTextChangeListener textChangeListener) {
            super(view);

            // 뷰 초기화
            ingredient = view.findViewById(R.id.editText_recipe_write_ingredient_itemlist_01);
            countE = view.findViewById(R.id.editText_recipe_write_ingredient_itemlist_02);
            plus = view.findViewById(R.id.textView_recipe_write_ingredient_itemlist_01);
            minus = view.findViewById(R.id.textView_recipe_write_ingredient_itemlist_02);
            delete = view.findViewById(R.id.textView_recipe_write_ingredient_itemlist_03);

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
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countE.setText(Double.toString(Double.parseDouble(countE.getText().toString())+1.0));
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Double.parseDouble(countE.getText().toString())-1.0 >= 0)
                        countE.setText(Double.toString(Double.parseDouble(countE.getText().toString())-1.0));
                }
            });
            delete.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });
        }

        public void onBind(UserRecipeIngredient item, int position) {
            ingredient.setText(item.getIngredient());
            countE.setText(item.getCount());

            ingredient.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(textChangeListener != null)
                        textChangeListener.onTextChanged(position, s.toString(), ingredient);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            countE.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(textChangeListener != null)
                        textChangeListener.onTextChanged(position, s.toString(), countE);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public IngredientRecyclerViewAdaptor(ArrayList<UserRecipeIngredient> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public IngredientRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_write_ingredient_itemlist, parent, false);
        return new IngredientRecyclerViewAdaptor.ViewHolder(view, listener, textChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientRecyclerViewAdaptor.ViewHolder holder, int position) {
        holder.onBind(dataSet.get(position), position);

        //((ViewHolder) holder).recipe_image.getLayoutParams().height = 400;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
