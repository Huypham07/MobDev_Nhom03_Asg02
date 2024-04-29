package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class AccountInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        //test
        RoundCornerProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(4810000);
    }
}