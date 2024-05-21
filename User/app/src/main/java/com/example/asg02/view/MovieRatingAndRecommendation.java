package com.example.asg02.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asg02.R;
import com.example.asg02.model.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieRatingAndRecommendation extends AppCompatActivity {
    ImageButton back;
    Spinner chooseTypeSpinner;
    RecyclerView movieListView;
    List<Movie> movieRatingList;
    List<Movie> movieRecommendationList;
    MovieRatingAndRecommendationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating_and_recommendation);
        back = findViewById(R.id.backFromMovieRatingAndRecommendation);
        chooseTypeSpinner = findViewById(R.id.chooseType);
        movieListView = findViewById(R.id.ratingAndRecommendationRecyclerView);

        movieRatingList = new ArrayList<>();
        movieRecommendationList = new ArrayList<>();

        adapter = new MovieRatingAndRecommendationAdapter(this, movieRatingList);
        movieListView.setLayoutManager(new LinearLayoutManager(this));
        movieListView.setAdapter(adapter);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(MovieRatingAndRecommendation.this, MainActivity.class);
            startActivity(intent);
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Movies");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                movieRatingList.clear();
                movieRecommendationList.clear();
                for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                    Movie movie = movieSnapshot.getValue(Movie.class);
                    if (movie != null) {
                        movieRatingList.add(movie);
                        movieRecommendationList.add(movie);
                        Log.e("Success", "success");
                    } else {
                        Log.e("Failed", "failed");
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });

        String[] chooseTypeArray = {"Đánh giá", "Phù hợp"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, chooseTypeArray);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
        chooseTypeSpinner.setAdapter(spinnerAdapter);

        chooseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(MovieRatingAndRecommendation.this, "Bạn đã chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
                if (selectedItem.equals("Đánh giá")) {
                    adapter.notifyDataSetChanged();
                } else if (selectedItem.equals("Phù hợp")) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
