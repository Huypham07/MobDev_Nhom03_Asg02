package com.example.asg02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ForgotPasswordActivity3 extends AppCompatActivity {

    private boolean isHidePassword = true;
    private boolean isHideConfirmPassword = true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v_back -> {
            onBackPressed();
        });

        EditText newPassword = findViewById(R.id.new_password);
        changePasswordVisibility(newPassword, isHidePassword);

        newPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (newPassword.getRight() - newPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 32)) {
                    isHidePassword = !isHidePassword;
                    changePasswordVisibility(newPassword, isHidePassword);
                    return true;
                }
            }
            return false;
        });

        EditText confirmPassword = findViewById(R.id.confirm_new_password);
        changePasswordVisibility(confirmPassword, isHideConfirmPassword);

        confirmPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (confirmPassword.getRight() - confirmPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 32)) {
                    isHideConfirmPassword = !isHideConfirmPassword;
                    changePasswordVisibility(confirmPassword, isHideConfirmPassword);
                    return true;
                }
            }
            return false;
        });

        Button continue_ = findViewById(R.id.finish_reset_password);
        continue_.setOnClickListener(v -> {
            startActivity(new Intent(ForgotPasswordActivity3.this, LoginActivity.class));
        });
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
    @Override
    public void onBackPressed() {
        // custom
        super.onBackPressed();
    }
}