package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ManagerAddMovieActivity extends AppCompatActivity {
    ImageButton back;
    ImageView selectPosterImageView, setPosterImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_movie);
        back = findViewById(R.id.backMovie);
        selectPosterImageView = findViewById(R.id.selectPoster);
        setPosterImageView = findViewById(R.id.setPoster);
        back.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(ManagerAddMovieActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        selectPosterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            setPosterImageView.setImageURI(imageUri);
        }
    }
}