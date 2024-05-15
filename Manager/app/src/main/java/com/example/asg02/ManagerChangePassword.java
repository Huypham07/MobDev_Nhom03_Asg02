package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ManagerChangePassword extends AppCompatActivity {
    ImageButton back;
    EditText enterOldPasswordEditText, enterNewPasswordEditText, enterNewPasswordAgainEditText;
    Button confirmChangePasswordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_change_password);
        back = findViewById(R.id.backChangePassword);
        enterOldPasswordEditText = findViewById(R.id.enterOldPassword);
        enterNewPasswordEditText = findViewById(R.id.enterNewPassword);
        enterNewPasswordAgainEditText = findViewById(R.id.enterNewPasswordAgain);
        confirmChangePasswordButton = findViewById(R.id.confirmChangePassword);

    }
}