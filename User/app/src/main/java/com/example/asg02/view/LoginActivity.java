package com.example.asg02.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.Bundle;
import com.example.asg02.R;
import com.example.asg02.controller.LoginController;
import com.example.asg02.databinding.ActivityLoginBinding;
import com.example.asg02.model.User;

public class LoginActivity extends BaseActivity {
    private boolean isHidePassword = true;
    private ActivityLoginBinding binding;
    private LoginController loginController;
    private EditText editPassword;
    private EditText editId;
    private Button loginButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // assign
        setSupportActionBar(binding.toolbar);
        loginController = new LoginController();
        editId = binding.editId;
        editPassword = binding.editPassword;
        loginButton = binding.loginButton;
        ImageView passwordIcon = binding.hidePasswordButton;

        editId.addTextChangedListener(Utils.afterEditTextChanged(editId, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editId.getText().toString().isEmpty()) {
                    editId.setError("Vui lòng nhập số điện thoại hoặc email!");
                } else {
                    editId.setError(null);
                }
                if (isEnableLoginButton()) {
                    loginButton.setEnabled(true);
                } else {
                    loginButton.setEnabled(false);
                }
            }
        }));

        changePasswordVisibility(passwordIcon, editPassword, isHidePassword);
        editPassword.addTextChangedListener(Utils.afterEditTextChanged(editPassword, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPassword.getText().toString().isEmpty()) {
                    editPassword.setError("Vui lòng nhập mật khẩu!");
                } else {
                    editPassword.setError(null);
                }
                if (isEnableLoginButton()) {
                    loginButton.setEnabled(true);
                } else {
                    loginButton.setEnabled(false);
                }
            }
        }));

        passwordIcon.setOnClickListener(v -> {
            isHidePassword = !isHidePassword;
            changePasswordVisibility(passwordIcon, editPassword, isHidePassword);
        });

        loginButton.setOnClickListener(v -> {
            String id = editId.getText().toString();
            String password = editPassword.getText().toString();
            binding.progressBar.setVisibility(View.VISIBLE);
            loginController.login(id, password).thenAccept(account -> {
                User user = (User) account;
                if (user != null) {
                    binding.errorLogin.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else {
                    binding.errorLogin.setVisibility(View.VISIBLE);
                }
                binding.progressBar.setVisibility(View.GONE);
            });
        });

        binding.forgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, PasswordRecoveryActivity.class));
            finish();
        });

        binding.registerButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

    }

    private boolean isEnableLoginButton() {
        return !editId.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty();
    }
    private void changePasswordVisibility(ImageView imageView, EditText editText, boolean isHidePassword) {
        if (isHidePassword) {
            imageView.setImageResource(R.drawable.hide_password_icon);
            editText.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            imageView.setImageResource(R.drawable.show_password_icon);
            editText.setTransformationMethod(null);
        }
    }
}