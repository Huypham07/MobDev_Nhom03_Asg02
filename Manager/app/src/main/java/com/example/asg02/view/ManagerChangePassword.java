package com.example.asg02.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.asg02.R;
import com.example.asg02.controller.ChangePasswordController;

public class ManagerChangePassword extends AppCompatActivity {
    ImageButton back;
    EditText enterOldPasswordEditText, enterNewPasswordEditText, enterNewPasswordAgainEditText;
    Button confirmChangePasswordButton;
    ChangePasswordController changePasswordController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_change_password);
        back = findViewById(R.id.backChangePassword);
        enterOldPasswordEditText = findViewById(R.id.enterOldPassword);
        enterNewPasswordEditText = findViewById(R.id.enterNewPassword);
        enterNewPasswordAgainEditText = findViewById(R.id.enterNewPasswordAgain);
        confirmChangePasswordButton = findViewById(R.id.confirmChangePassword);
        changePasswordController = new ChangePasswordController(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerChangePassword.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        confirmChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("account_info", MODE_PRIVATE);
                String password = preferences.getString("password", "error"); // Provide a default value if email is not found
                if (password != null) {
                    // Email was found in SharedPreferences
                    Log.d("SharedPrefs", "Password retrieved: " + password);
                    // Use the retrieved email here (e.g., display it, send it to a server)
                } else {
                    // Email was not found in SharedPreferences
                    Log.d("SharedPrefs", "Password not found in SharedPreferences");
                    // Handle the case where email is not available (e.g., prompt user to log in)
                }
                if (password.equals(enterOldPasswordEditText.getText().toString())
                && enterNewPasswordEditText.getText().toString().equals(enterNewPasswordAgainEditText.getText().toString())) {
                    changePasswordController.changePassword(enterNewPasswordEditText.getText().toString());
                    Toast.makeText(ManagerChangePassword.this, "Đúng", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ManagerChangePassword.this, ManagerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ManagerChangePassword.this, "Vui lòng kiểm tra lại mật khẩu cũ và mật khẩu mới", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}