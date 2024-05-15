package com.example.asg02.view.ui.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.asg02.R;
import com.example.asg02.databinding.FragmentMovieDetailsBinding;
import com.example.asg02.model.Movie;
import com.example.asg02.view.MainActivity;
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

        binding.movieName.setText(movie.getName().toUpperCase());
        binding.movieRating.setRating(movie.getRating());
        binding.movieRating.setOnTouchListener((v, event) -> true);
        binding.movieDuration.setText(Utils.convertIntTimeToString(movie.getDurationMins()));
        binding.releaseDate.setText(movie.getReleaseDate());
//        binding.genre.setText(movie.getGenre());
        binding.language.setText(movie.getLanguage());
        binding.description.setText(movie.getDescription());
//        binding.actors.setText(movie.getActors());
//        binding.director.setText(movie.getDirector());

        if (movie.getPoster() != null) {
            String trailerLink = movie.getTrailerLink();
            String embedLink = Utils.createEmbedLinkFromYoutube(trailerLink);
            WebView webView = binding.trailerVideo;
            webView.loadData(embedLink, "text/html", "utf-8");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
        }

        //test
        else {
            String trailerLink = "https://www.youtube.com/watch?v=dEyB00oEA04";
            String embedLink = Utils.createEmbedLinkFromYoutube(trailerLink);
            WebView webView = binding.trailerVideo;
            webView.loadData(embedLink, "text/html", "utf-8");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
        }

        binding.bookingButton.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            Bundle bundle = new Bundle();
            bundle.putInt("movieId", movie.getId());
            controller.navigate(R.id.nav_choose_complex, bundle);
        });

        binding.linearLayout.setOnTouchListener((v, event) -> {
            ((MainActivity) getActivity()).hideKeyboard();
            return false;
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}