package com.example.asg02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class RegisterActivity extends AppCompatActivity {
    private boolean isHidePassword = true;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v_back -> {
            onBackPressed();
        });

        EditText password = findViewById(R.id.edit_password_register);
        changePasswordVisibility(password, isHidePassword);
        password.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (password.getRight() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    isHidePassword = !isHidePassword;
                    changePasswordVisibility(password, isHidePassword);
                    return true;
                }
            }
            return false;
        });

        Button register = findViewById(R.id.register);
        register.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    @Override
    public void onBackPressed() {
        // custom
        super.onBackPressed();
    }

    private void changePasswordVisibility(EditText editText, boolean isHidePassword) {
        if (isHidePassword) {
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.hide_password_icon), null);
            editText.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.show_password_icon), null);
            editText.setTransformationMethod(null);
        }
    }
}