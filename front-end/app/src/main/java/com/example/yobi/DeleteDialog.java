package com.example.yobi;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

interface DialogListener {
    public void onPositiveClicked();
    public void onNegativeClicked();
}

public class DeleteDialog extends Dialog {
    private DialogListener dialogListener;
    private AppCompatButton yes, no;
    private TextView message;

    public void setDialogListener(DialogListener dialogListener) { this.dialogListener = dialogListener; }
    public DeleteDialog(@NonNull Context context, String contents) {
        super(context);
        setContentView(R.layout.custom_dialog);

        message = findViewById(R.id.message);
        message.setText(contents);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        // 예 클릭시
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.onPositiveClicked();
            }
        });

        // 아니오 클릭시
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.onNegativeClicked();
            }
        });
    }
}
