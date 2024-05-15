package com.example.asg02.view.ui.home.movieOverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.asg02.R;
import com.example.asg02.controller.movie.GetMovieController;
import com.example.asg02.databinding.FragmentCurrentMovieBinding;
import com.example.asg02.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class CurrentMovieFragment extends Fragment {

    private FragmentCurrentMovieBinding binding;
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private MovieAdapter movieAdapter;
    private GetMovieController movieController;

    private int currPos = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCurrentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        movieController = new GetMovieController();

        movieList = movieController.getAllCurrentMovies();
        if (movieList == null) {
            movieList = new ArrayList<>();
        }

        movieAdapter = new MovieAdapter(movieList);

        recyclerView = binding.recyclerview;
        recyclerView.setAdapter(movieAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        movieAdapter.setOnItemClickListener(pos -> {
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movieList.get(pos));
            controller.navigate(R.id.action_nav_home_to_nav_movie_details, bundle);

        });

        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                currPos = snapHelper.findTargetSnapPosition(recyclerView.getLayoutManager(), velocityX, velocityY);
                return true;
            }
        });

        binding.bookingButton.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movieId", movieList.get(currPos));
            controller.navigate(R.id.nav_choose_complex, bundle);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}