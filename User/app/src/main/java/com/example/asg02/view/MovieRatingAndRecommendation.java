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
import com.example.asg02.model.Booking;
import com.example.asg02.model.Movie;
import com.example.asg02.model.Show;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieRatingAndRecommendation extends AppCompatActivity {
    ImageButton back;
    String userId;
    Spinner chooseTypeSpinner;
    RecyclerView movieListView;
    List<Movie> movieRatingList;
    List<Movie> movieRecommendationList;
    List<Movie> movieWatchedByCustomerList;
    List<Integer> movieIdWatchedByCustomerList;

    MovieRecommender movieRecommender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating_and_recommendation);
        back = findViewById(R.id.backFromMovieRatingAndRecommendation);
        chooseTypeSpinner = findViewById(R.id.chooseType);
        movieListView = findViewById(R.id.ratingAndRecommendationRecyclerView);

        movieRatingList = new ArrayList<>();
        movieRecommendationList = new ArrayList<>();
        movieWatchedByCustomerList = new ArrayList<>();
        movieIdWatchedByCustomerList = new ArrayList<>();
        movieRecommender = new MovieRecommender();

        Intent getResultIntent = getIntent();
        if (getResultIntent != null) {
            userId = getResultIntent.getStringExtra("userId");
        }

        back.setOnClickListener(v -> {
            Intent intent = new Intent(MovieRatingAndRecommendation.this, MainActivity.class);
            startActivity(intent);
        });

        // Load data and then initialize recommendation list
        loadBookingData(userId, () -> {
            loadMovieData(() -> {
                if (movieRecommendationList == null) {
                    Log.e("Before", "movieRecommendationList null");
                } else {
                    Log.e("Before", "movieRecommendationList isn't null. \n"
                            + movieRecommendationList.toString()
                            + "\n" + movieWatchedByCustomerList.toString());

                }
                movieRecommendationList = movieRecommender.recommendMovies(movieRecommendationList, movieWatchedByCustomerList);
                if (movieRecommendationList == null) {
                    Log.e("After", "movieRecommendationList null");
                } else {
                    Log.e("After", "movieRecommendationList isn't null. \n"
                            + movieRecommendationList.toString()
                            + "\n" + movieWatchedByCustomerList.toString());
                }
                setupRecyclerView();
            });
        });

        setupSpinner();
    }

    private void loadBookingData(String userId, Runnable callback) {
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Bookings");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                    Booking booking = bookingSnapshot.getValue(Booking.class);
                    if (booking != null) {
                        String userIdFromBooking = booking.getUserID();
                        Show show = booking.getShow();
                        if (userId.equals(userIdFromBooking)) {
                            movieIdWatchedByCustomerList.add(show.getMovieId());
                        }
                        Log.e("Success", userId + " " + userIdFromBooking + " \nmovieId:" + show.getMovieId());
                    }
                }
                callback.run();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void loadMovieData(Runnable callback) {
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
                        if (movieIdWatchedByCustomerList.contains(movie.getId())) {
                            movieWatchedByCustomerList.add(movie);
                        }
                    }
                }
                callback.run();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void setupRecyclerView() {
        MovieRatingAndRecommendationAdapter adapter1;
        adapter1 = new MovieRatingAndRecommendationAdapter(this, movieRecommendationList);
        movieListView.setLayoutManager(new LinearLayoutManager(this));
        movieListView.setAdapter(adapter1);
    }

    private void setupSpinner() {
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
                    Log.e("Success", "success rating");
                } else if (selectedItem.equals("Phù hợp")) {
                    Log.e("Success", "success recommendation");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}