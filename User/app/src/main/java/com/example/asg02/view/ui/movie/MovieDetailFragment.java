package com.example.asg02.view.ui.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asg02.databinding.FragmentMovieDetailsBinding;
import com.example.asg02.model.Movie;
import com.example.asg02.view.Utils;

public class MovieDetailFragment extends Fragment {

    private static final String ARG_PARAM = "movie";
    private Movie movie;
    private FragmentMovieDetailsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        movie = (Movie) getArguments().getSerializable(ARG_PARAM);
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.movieName.setText(movie.getName());
        binding.movieRating.setRating(movie.getRating());
        binding.movieRating.setOnTouchListener((v, event) -> true);
        binding.movieDuration.setText(Utils.getFormattedDuration(movie.getDurationMins()));
        binding.releaseDate.setText(movie.getReleaseDate());
//        binding.genre.setText(movie.getGenre());
        binding.language.setText(movie.getLanguage());
        binding.description.setText(movie.getDescription());
//        binding.actors.setText(movie.getActors());
//        binding.director.setText(movie.getDirector());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}