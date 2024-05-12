package com.example.asg02;

import static com.example.asg02.model.Movie.encodeImage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.asg02.controller.CreateMovieController;
import com.example.asg02.model.Movie;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ManagerAddMovieActivity extends AppCompatActivity {
    ImageButton back;
    Button add;
    EditText enterTitle, enterReleased, enterRuntime, enterGenre, enterDirector, enterWriter,
            enterActors, enterPlot, enterLanguage, enterTrailerLink;
    ImageView selectPosterImageView, setPosterImageView;
    private CreateMovieController createMovieController;
    private String name;
    private String poster;
    private String genre;
    private String durationMins;
    private String releaseDate;
    private String description;
    private String director;
    private String actors;
    private String language;
    private String trailerLink;
    private float rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_movie);
        back = findViewById(R.id.backMovie);
        selectPosterImageView = findViewById(R.id.selectPoster);
        setPosterImageView = findViewById(R.id.setPoster);
        add = findViewById(R.id.add);
        enterTitle = findViewById(R.id.enterTitle);
        enterReleased = findViewById(R.id.enterReleased);
        enterRuntime = findViewById(R.id.enterRuntime);
        enterGenre = findViewById(R.id.enterGenre);
        enterDirector = findViewById(R.id.enterDirector);
        enterWriter = findViewById(R.id.enterWriter);
        enterActors = findViewById(R.id.enterActors);
        enterPlot = findViewById(R.id.enterPlot);
        enterLanguage = findViewById(R.id.enterLanguage);
        enterTrailerLink = findViewById(R.id.enterTrailerLink);
        createMovieController = new CreateMovieController(this);

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
                startActivityForResult(intent, 1);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = enterTitle.getText().toString();
                genre = enterGenre.getText().toString();
                durationMins = enterRuntime.getText().toString();
                releaseDate = enterReleased.getText().toString();
                description = enterPlot.getText().toString();
                director = enterDirector.getText().toString();
                actors = enterActors.getText().toString();
                language = enterLanguage.getText().toString();
                trailerLink = enterTrailerLink.getText().toString();
                rating = 5;
                Movie movie = new Movie(name, poster, genre, durationMins, releaseDate, description, description, actors, language, trailerLink, rating);
                createMovieController.createMovie(movie);
                Intent intent = new Intent(ManagerAddMovieActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap posterBitmap = BitmapFactory.decodeStream(inputStream);
                setPosterImageView.setImageBitmap(posterBitmap);
                poster = encodeImage(posterBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}