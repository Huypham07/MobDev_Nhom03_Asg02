package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asg02.model.Account;

public class InfoActivity extends AppCompatActivity {
    ImageButton back;
    Button changeNameButton, changePasswordButton;
    TextView nameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        back = findViewById(R.id.backInfo);
        changeNameButton = findViewById(R.id.changeName);
        changePasswordButton = findViewById(R.id.changePassword);
        nameTextView =  findViewById(R.id.name);
        SharedPreferences preferences = getSharedPreferences("account_info", MODE_PRIVATE);
        String name = preferences.getString("name", null);
        nameTextView.setText("Tên doanh nghiệp: " + name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });

    }
}