package com.example.asg02.view.ui.home.movieOverview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.asg02.R;
import com.example.asg02.controller.GetMovieController;
import com.example.asg02.databinding.FragmentCurrentMovieBinding;
import com.example.asg02.model.Movie;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;

public class CurrentMovieFragment extends Fragment {

    private FragmentCurrentMovieBinding binding;
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private MovieAdapter movieAdapter;
    private GetMovieController movieController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCurrentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        movieController = new GetMovieController();

        movieList = movieController.getAllCurrentMovies();

        movieAdapter = new MovieAdapter(movieList);
        movieAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                controller.navigate(R.id.nav_movie);
            }
        });

        recyclerView = binding.recyclerview;
        recyclerView.setAdapter(movieAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}