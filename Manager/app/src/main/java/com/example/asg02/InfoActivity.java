package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asg02.controller.InfoController;
import com.example.asg02.model.Account;

public class InfoActivity extends AppCompatActivity {
    ImageButton back;
    Button changeNameButton, changePasswordButton;
    EditText nameEditView;
    InfoController infoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        back = findViewById(R.id.backInfo);
        changeNameButton = findViewById(R.id.changeName);
        nameEditView =  findViewById(R.id.nameCompany);
        SharedPreferences preferences = getSharedPreferences("account_info", MODE_PRIVATE);
        String name = preferences.getString("name", null);
        String email = preferences.getString("email", null);
        nameEditView.setText("Tên doanh nghiệp: " + name);
        infoController = new InfoController(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        changeNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = nameEditView.getText().toString();
                s = s.substring(s.indexOf(':') + 1, s.length());
                SharedPreferences preferences = getSharedPreferences("account_info", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", s);
                editor.apply();
                Toast.makeText(infoController.getContext(), "Name updated successfully", Toast.LENGTH_SHORT).show();
                infoController.updateManagerNameByEmail(email, s);
                Intent intent = new Intent(InfoActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}