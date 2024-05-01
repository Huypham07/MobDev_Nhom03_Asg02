package com.example.asg02;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ForgotPasswordActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v_back -> {
            onBackPressed();
        });

        TextView resendCode = findViewById(R.id.resend_code);
        resendCode.setOnClickListener(v_ -> {
            // Todo
        });

        Button continue_ = findViewById(R.id.continue_step_3);
        continue_.setOnClickListener(v -> {
            startActivity(new Intent(ForgotPasswordActivity2.this, ForgotPasswordActivity3.class));
        });


    }

    @Override
    public void onBackPressed() {
        // custom
        super.onBackPressed();
    }
}