package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asg02.controller.LoginController;
import com.example.asg02.model.Manager;


public class LoginActivity extends AppCompatActivity {
    private boolean isHidePassword = true;
    private LoginController loginController;
    private EditText editPassword;
    private EditText editEmail;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginController = new LoginController();
        editEmail = findViewById(R.id.enterEmail);
        editPassword = findViewById(R.id.enterPassword);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
            } else {
                loginController.login(email, password).thenApply(account -> {
                    Manager manager = (Manager) account;
                    if (manager != null) {
                        Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("account_info", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", manager.getEmail());
                        editor.putString("password", manager.getPassword());
                        editor.putString("name", manager.getName());
                        editor.commit();
                        intent.putExtra("managers", manager);
                        Log.e("a", manager.getEmail());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Tài khoản, mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                    return null;
                });
            }

        });
    }
}