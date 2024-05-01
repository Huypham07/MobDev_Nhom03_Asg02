package com.example.asg02;

import android.content.Intent;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ForgotPasswordActivity1 extends AppCompatActivity {
    private boolean isFindByPhoneNumber = true;
    private TextView instruction;
    private EditText input;
    private TextView changeInputTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v_back -> {
            onBackPressed();
        });

        instruction = findViewById(R.id.instruction);

        input = findViewById(R.id.input);

        changeInputTypeText = findViewById(R.id.change_type_input);
        changeInputType(isFindByPhoneNumber);

        changeInputTypeText.setOnClickListener(v_ -> {
            isFindByPhoneNumber = !isFindByPhoneNumber;
            changeInputType(isFindByPhoneNumber);
        });

        Button continue_ = findViewById(R.id.continue_step_2);
        continue_.setOnClickListener(v -> {
            startActivity(new Intent(ForgotPasswordActivity1.this, ForgotPasswordActivity2.class));
        });
    }

    @Override
    public void onBackPressed() {
        // custom
        super.onBackPressed();
    }

    private void changeInputType(boolean isFindByPhoneNumber) {
        if (isFindByPhoneNumber) {
            input.setInputType(InputType.TYPE_CLASS_PHONE);
            input.setHint("Số điện thoại");
            changeInputTypeText.setText("Tìm kiếm bằng email");
            instruction.setText("Vui lòng nhập " + "số di động " + "của bạn.");
        } else {
            input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            input.setHint("Email");
            changeInputTypeText.setText("Tìm kiếm bằng số điện thoại");
            instruction.setText("Vui lòng nhập " + "Email " + "của bạn.");
        }
    }
}