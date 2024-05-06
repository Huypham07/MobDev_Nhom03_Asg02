package com.example.asg02.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asg02.R;
import com.example.asg02.controller.LoginController;
import com.example.asg02.databinding.ActivityLoginBinding;
import com.example.asg02.model.Account;

public class LoginActivity extends AppCompatActivity {
    private boolean isHidePassword = true;
    private ActivityLoginBinding binding;
    private LoginController loginController;
    private EditText editPassword;
    private EditText editId;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityLoginBinding.inflate(getLayoutInflater());
//
//        setContentView(binding.getRoot());

        setContentView(R.layout.activity_login);

        loginController = new LoginController();

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(v -> {
            String id = editId.getText().toString();
            String password = editPassword.getText().toString();
            Account account = loginController.login(id, password);
            if (account != null) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        TextView forgotPassword = findViewById(R.id.forgot_password);

        forgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, PasswordRecoveryActivity.class));
            finish();
        });

        Button registerButton = findViewById(R.id.register_account);
        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });




        EditText password = findViewById(R.id.edit_password);
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