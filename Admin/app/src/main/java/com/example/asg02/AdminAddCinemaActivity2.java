package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.asg02.controller.RegisterController;
import com.example.asg02.model.Manager;
import com.google.android.material.textfield.TextInputLayout;

public class AdminAddCinemaActivity2 extends BaseActivity {

    ImageButton backBtn;
    EditText emailName;
    EditText password;
    Button finishBtn;
    String emailNameText = "@star3.cineplex.com";
    String passwordText = "#star3cineplex#";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_cinema2);

        backBtn = findViewById(R.id.backBtn3);
        emailName = findViewById(R.id.emailName);
        password = findViewById(R.id.password);
        finishBtn = findViewById(R.id.finishBtn);

        String extra = getIntent().getExtras().getString("CinemaName", "cinema name");
        String cinemaName = extra;
        cinemaName = cinemaName.replaceAll(" ", "");

        emailNameText = cinemaName + emailNameText;
        passwordText = passwordText + cinemaName.toLowerCase();

        emailName.setText(emailNameText);
        password.setText(passwordText);

        emailName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                emailNameText = emailName.getText().toString();
                finishBtn.setEnabled(!emailNameText.isEmpty());
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                passwordText = password.getText().toString();
                finishBtn.setEnabled(!passwordText.isEmpty());
            }
        });

        finishBtn.setOnClickListener(v -> {
            new RegisterController().register(new Manager(emailNameText, passwordText));
            String textToCopy = "Tài khoản: " + emailNameText + "\nMật khẩu: " + passwordText;
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", textToCopy);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminAddCinemaActivity.class);
            intent.putExtra("CinemaName", extra);
            startActivity(intent);
        });
    }
}