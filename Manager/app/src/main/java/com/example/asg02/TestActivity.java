package com.example.asg02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.set);
        button = findViewById(R.id.get);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

// Tạo tham chiếu đến database Movies
        DatabaseReference moviesRef = database.getReference("Movies");

// Đọc dữ liệu từ database Movies
        moviesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder movieInfo = new StringBuilder(); // Use StringBuilder for efficient string concatenation

                for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                    // Access data for each movie entry
                    String title = movieSnapshot.child("Title").getValue().toString();
                    String year = movieSnapshot.child("Year").getValue().toString();
                    // ... (access other fields as needed)

                    // Add movie info to StringBuilder
                    movieInfo.append("Title: ").append(title).append("\n")
                            .append("Year: ").append(year).append("\n\n"); // Add line breaks for separation
                }

                // Set TextView content with the accumulated movie info
                textView.setText(movieInfo.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle data reading error
            }
        });
        Map<String, Object> newMovieData = new HashMap<>();
        newMovieData.put("Title", "The Shawshank Redemption"); // Movie title
        newMovieData.put("Year", "1994"); // Release year
        newMovieData.put("Rated", "R"); // MPAA rating
        newMovieData.put("Realeased","18 Dec 2009");
        newMovieData.put("Runtime", "142 min"); // Running time
        newMovieData.put("Genre", "Drama"); // Primary genre
        newMovieData.put("Director", "Frank Darabont"); // Director's name
        newMovieData.put("Writer", "....");
        newMovieData.put("Actors", "Tim Robbins, Morgan Freeman, William Sadler"); // List of main actors (comma-separated)
        newMovieData.put("Plot", "... (Add a brief plot summary here)"); // Short description of the movie

// Add additional details if available (optional)
        newMovieData.put("Language", "English"); // Primary language
        newMovieData.put("Awards", "7 Oscars, 2 Golden Globes, 25 wins & 71 nominations");
         // Awards received
        newMovieData.put("Metascore", "88"); // Metacritic score
        newMovieData.put("imdbRating", "9.3"); // IMDb rating
        newMovieData.put("imdbVotes", "2.7M"); // Number of IMDb votes
        newMovieData.put("imdbID", "tt0111161"); // IMDb ID

// Get a reference to the "Movies" node

// Push the new movie data to the database
        moviesRef.push().setValue(newMovieData);
    }
}