package com.example.admin;

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

import com.example.admin.controller.RegisterController;
import com.example.admin.model.Manager;

public class AdminAddCinemaActivity2 extends BaseActivity {

    ImageButton backBtn;
    EditText emailName;
    EditText password;
    Button finishBtn;
    String emailNameText = "@star3.cineplex.com";
    String passwordText = "#star3cineplex#";
    String cinemaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_cinema2);

        backBtn = findViewById(R.id.backBtn3);
        emailName = findViewById(R.id.emailName);
        password = findViewById(R.id.password);
        finishBtn = findViewById(R.id.finishBtn);

//        String extra = getIntent().getExtras().getString("CinemaName", "cinema name");
//        cinemaName = extra;
        Bundle extras = getIntent().getExtras();
        String extra;
        if (extras != null) {
            extra = extras.getString("CinemaName", "cinema name");
            cinemaName = extra;
        } else {
            extra = null;
            cinemaName = "cinemaname"; // Giá trị mặc định nếu không có extra
        }

        emailNameText = cinemaName.replaceAll(" ", "") + emailNameText;
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
            new RegisterController().register(new Manager(emailNameText, passwordText, cinemaName));
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