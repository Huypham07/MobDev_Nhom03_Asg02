package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AdminAddCinemaActivity extends AppCompatActivity {

    ImageButton backBtn;
    Button continueBtn;
    EditText enterCinemaName;
    String cinemaName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_cinema);

        backBtn = findViewById(R.id.backBtn2);
        continueBtn = findViewById(R.id.continueBtn);
        enterCinemaName = findViewById(R.id.enterCinemaName);

        enterCinemaName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                cinemaName = enterCinemaName.getText().toString();
                continueBtn.setEnabled(!cinemaName.isEmpty());
            }
        });

        continueBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminAddCinemaActivity2.class);
            intent.putExtra("CinemaName", cinemaName);
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
        });
    }
}