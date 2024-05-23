package com.example.user.view.ui.movie;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.databinding.FragmentOrderByMovieBinding;
import com.example.user.model.Booking;
import com.example.user.model.Movie;
import com.example.user.model.MovieRecommender;
import com.example.user.utils.DateTimeUtils;
import com.example.user.utils.FirebaseUtils;
import com.example.user.view.ui.movie.adapter.MovieRatingAndRecommendationAdapter;
import com.example.user.vm.AccountViewModel;
import com.example.user.vm.BaseViewModel;
import com.example.user.vm.BookingViewModel;
import com.example.user.vm.ListBookingViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderByMovieFragment extends Fragment {
    private FragmentOrderByMovieBinding binding;
    private BaseViewModel baseViewModel;
    private AccountViewModel accountViewModel;
    private BookingViewModel bookingViewModel;
    private ListBookingViewModel listBookingViewModel;
    private String userId;
    private RecyclerView recyclerView;
    private MovieRatingAndRecommendationAdapter movieRatingAndRecommendationAdapter;
    private List<Movie> movieRatingList = new ArrayList<>();
    private List<Movie> movieRecommendationList = new ArrayList<>();
    private List<Movie> movieWatchedByCustomerList = new ArrayList<>();
    private List<Integer> movieIdWatchedByCustomerList = new ArrayList<>();
    private MovieRecommender movieRecommender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baseViewModel = new ViewModelProvider(requireActivity()).get(BaseViewModel.class);
        accountViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
        bookingViewModel = new ViewModelProvider(requireActivity()).get(BookingViewModel.class);
        listBookingViewModel = new ViewModelProvider(requireActivity()).get(ListBookingViewModel.class);

        binding = FragmentOrderByMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        movieRecommender = new MovieRecommender();

        recyclerView = binding.recyclerview;
        movieRatingAndRecommendationAdapter = new MovieRatingAndRecommendationAdapter(movieRatingList);
        recyclerView.setAdapter(movieRatingAndRecommendationAdapter);

        movieRatingAndRecommendationAdapter.setOnItemClickListener(position -> {
            bookingViewModel.setMovie(movieRatingList.get(position));
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_movie_details);
        });

        accountViewModel.getUserId().observe(
                getViewLifecycleOwner(),
                userId -> {
                    this.userId = userId;
                    updateUI();
                }
        );


        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateUI() {
        if (userId == null) {
            return;
        }
        new Thread(() -> {

        }).start();
        listBookingViewModel.setUserId(userId);
        listBookingViewModel.isDataReady().observe(
                getViewLifecycleOwner(),
                isReady -> {
                    if (isReady) {
                        List<Booking> bookings = listBookingViewModel.getBookingList().getValue();
                        for (Booking b : bookings) {
                            movieIdWatchedByCustomerList.add(b.getShow().getMovieId());
                        }
                        movieRatingList.clear();
                        movieRatingList.addAll(baseViewModel.getMovieList().getValue());
                        for (Movie m : movieRatingList) {
                            if (movieIdWatchedByCustomerList.contains(m.getId())) {
                                movieWatchedByCustomerList.add(m);
                            }
                        }
                        movieRatingList.sort((movie1, movie2) -> Float.compare(movie2.getRating(), movie1.getRating()));
                        String s = "";
                        for (int i = 0; i < movieRatingList.size(); i++) {
                            s = s + " " + movieRatingList.get(i).getName();
                        }
                        Log.d("BeforeRecommendation", "movieRatingList: " + s);
                        List<Movie> clone = new ArrayList<>();
                        clone.addAll(movieRatingList);
                        movieRecommendationList = movieRecommender.recommendMovies(clone, movieWatchedByCustomerList);
                        s = "";
                        for (int i = 0; i < movieRecommendationList.size(); i++) {
                            s = s + " " + movieRecommendationList.get(i).getName();
                        }
                        Log.d("AfterRecommendation", "movieRecommendationList: " + s);
                        movieRatingAndRecommendationAdapter.notifyDataSetChanged();
                    }
                }
        );
    }
}