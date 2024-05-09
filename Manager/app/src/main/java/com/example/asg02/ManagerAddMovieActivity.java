package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ManagerAddMovieActivity extends AppCompatActivity {
    ImageButton back;
    Button add;
    EditText enterTitle, enterReleased, enterRuntime, enterGenre, enterDirector, enterWriter,
            enterActors, enterPlot, enterLanguage, enterTrailerLink;
    ImageView selectPosterImageView, setPosterImageView;
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
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference moviesRef = database.getReference("Movies");
                Map<String, Object> newMovieData = new HashMap<>();
                newMovieData.put("Title", enterTitle.getText().toString()); // Movie title
                newMovieData.put("Year", enterReleased.getText().toString()); // Release year
                newMovieData.put("Rated", "5"); // MPAA rating
                newMovieData.put("Realeased",enterReleased.getText().toString());
                newMovieData.put("Runtime", enterRuntime.getText().toString()); // Running time
                newMovieData.put("Genre", enterGenre.getText().toString()); // Primary genre
                newMovieData.put("Director", enterDirector.getText().toString()); // Director's name
                newMovieData.put("Writer", enterWriter.getText().toString());
                newMovieData.put("Actors", enterActors.getText().toString()); // List of main actors (comma-separated)
                newMovieData.put("Plot", enterPlot.getText().toString()); // Short description of the movie
                newMovieData.put("Language", enterLanguage.getText().toString()); // Primary language
                newMovieData.put("Awards", "7 Oscars, 2 Golden Globes, 25 wins & 71 nominations");
                newMovieData.put("Metascore", "18"); // Metacritic score
                newMovieData.put("imdbRating", "2.3"); // IMDb rating
                newMovieData.put("imdbVotes", "3.7M"); // Number of IMDb votes
                newMovieData.put("imdbID", "tt0111161"); // IMDb ID
                moviesRef.push().setValue(newMovieData);
                setPosterImageView.setDrawingCacheEnabled(true);
                setPosterImageView.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) setPosterImageView.getDrawable()).getBitmap();
                new AsyncTask<Void, Void, byte[]>() {
                    @Override
                    protected byte[] doInBackground(Void... voids) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        return baos.toByteArray();
                    }

                    @Override
                    protected void onPostExecute(byte[] data) {
                        // Sau khi chuyển đổi thành công, tiến hành tải hình ảnh lên Firebase Storage
                        uploadImageToFirebase(data);
                    }
                }.execute();
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
    private void uploadImageToFirebase(byte[] data) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        String fileName = enterTitle.getText().toString().replace(" ", "")+ "_poster.png";
        StorageReference imageRef = storageRef.child("posters/" + fileName);
        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
        }).addOnFailureListener(exception -> {
        });
    }
}