package com.example.asg02.view.ui.movie;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.asg02.utils.DateTimeUtils;
import com.example.asg02.utils.ImageUtils;
import com.example.asg02.utils.StringUtils;
import com.example.asg02.view.MainActivity;
import com.example.asg02.vm.BookingViewModel;

public class MovieDetailFragment extends Fragment {
    private BookingViewModel bookingViewModel;
    private FragmentMovieDetailsBinding binding;
    private Movie movie;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bookingViewModel = new ViewModelProvider(requireActivity()).get(BookingViewModel.class);

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bookingViewModel.getMovie().observe(
                getViewLifecycleOwner(),
                movie -> {
                    this.movie = movie;
                    updateUI();
                }

        );

        binding.bookingButton.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            controller.navigate(R.id.nav_select_complex);
        });

        binding.linearLayout.setOnTouchListener((v, event) -> {
            ((MainActivity) getActivity()).hideKeyboard();
            return false;
        });

        return root;
    }

    private void updateUI() {
        if (movie == null) {
            return;
        }
        binding.movieName.setText(movie.getName().toUpperCase());
        binding.movieRating.setRating(movie.getRating());
        binding.movieRating.setOnTouchListener((v, event) -> true);
        binding.movieDuration.setText(DateTimeUtils.convertMinsToStringTime(movie.getDurationMins()));
        binding.releaseDate.setText(movie.getReleaseDate());
        binding.genre.setText(movie.getGenre());
        binding.language.setText(movie.getLanguage());
        binding.description.setText(movie.getDescription());
        binding.actors.setText(movie.getActors());
        binding.director.setText(movie.getDirector());
        binding.censor.setText(movie.getCensor() + " - " + StringUtils.generateDetailsOfCensor(movie.getCensor()));
        binding.poster.setImageBitmap(ImageUtils.decodeBitmap(movie.getPoster()));

        String trailerLink = movie.getTrailerLink();
        String embedLink = StringUtils.createEmbedLinkFromYoutube(trailerLink);
        WebView webView = binding.trailerVideo;
        webView.loadData(embedLink, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        binding.bookingButton.setVisibility(
                DateTimeUtils.isAfterToday(movie.getReleaseDate()) ? View.GONE : View.VISIBLE
        );
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}