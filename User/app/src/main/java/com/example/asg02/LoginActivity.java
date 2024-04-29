package com.example.asg02;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        EditText editText = findViewById(R.id.editTextText3);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Xử lý sự kiện khi chỉ nhấn vào drawableRight ở bên phải
                        // Ví dụ: Hiển thị dialog, mở một activity, ...
                        return true; // Đã xử lý sự kiện, không cần chuyển tiếp sự kiện
                    }
                }
                return false; // Chuyển tiếp sự kiện để xử lý bình thường
            }
        });
         */
    }
}