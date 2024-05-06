package com.example.asg02.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.asg02.R;

public class Utils {
    public static Dialog createAskingDialog(String message, Context context, View.OnClickListener onConfirmListener) {
        // create dialog
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_asking_for_user_opinions);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView content = dialog.findViewById(R.id.content);
        content.setText(message);

        Button cancel = dialog.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button continue_ = dialog.findViewById(R.id.continue_action);
        continue_.setOnClickListener(onConfirmListener);

        return dialog;
    }
}
