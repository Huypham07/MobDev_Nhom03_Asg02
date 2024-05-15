package com.example.asg02.controller.movie;

import com.example.asg02.model.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetMovieController implements MovieReader {
    private FirebaseDatabase database;
    private LocalDate currentDate = LocalDate.now();

    public GetMovieController() {
        database = FirebaseDatabase.getInstance();
    }
    public List<Movie> getAllCurrentMovies() {
        List<Movie> currMovies = new ArrayList<>();
//        getAllMovies().thenAccept(movies -> {
//            if (movies != null) {
//                for (Movie movie : movies) {
//                    if (isCurrentMovie(movie.getReleaseDate())) {
//                        currMovies.add(movie);
//                    }
//                }
//            }
//        });

        currMovies.add(new Movie("The Matrix", null, "Action", 125, "30/03/1999"
                , "The Matrix is a 1999 science fiction action film written and directed by the Wachowskis. It is the first installment in The Matrix film series, starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, and Joe Pantoliano."
                , "Huy Pham", "Huy Pham, Huy Pham", "Tiếng Anh phụ đề Việt"));
        return currMovies;
    }

    public List<Movie> getAllUpcomingMovies() {
        List<Movie> upcomingMovies = new ArrayList<>();
        getAllMovies().thenAccept(movies -> {
            if (movies != null) {
                for (Movie movie : movies) {
                    if (!isCurrentMovie(movie.getReleaseDate())) {
                        upcomingMovies.add(movie);
                    }
                }
            }
        });
        return upcomingMovies;
    }

    @Override
    public CompletableFuture<List<Movie>> getAllMovies() {
        CompletableFuture<List<Movie>> future = new CompletableFuture<>();
        database.getReference("Movies").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Movie> movies = new ArrayList<>();
                for (DataSnapshot data : task.getResult().getChildren()) {
                    Movie movie = data.getValue(Movie.class);
                    movies.add(movie);
                }
                future.complete(movies);
            }
        });
        return future;
    }

    private boolean isCurrentMovie(String releaseDate) {
        if (releaseDate == null) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(releaseDate, formatter);

        if (date.isBefore(currentDate)) {
            return true;
        }
        return false;
    }
}
