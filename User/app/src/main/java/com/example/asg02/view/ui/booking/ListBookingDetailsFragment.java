package com.example.asg02.view.ui.booking;

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

import com.example.asg02.R;
import com.example.asg02.controller.booking.GetBookingController;
import com.example.asg02.controller.booking.UpdateBookingController;
import com.example.asg02.databinding.FragmentListBookingDetailsBinding;
import com.example.asg02.model.Booking;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.CinemaHall;
import com.example.asg02.model.Movie;
import com.example.asg02.utils.DateTimeUtils;
import com.example.asg02.utils.ViewUtils;
import com.example.asg02.vm.AccountViewModel;
import com.example.asg02.vm.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListBookingDetailsFragment extends Fragment {
    private boolean isExpired = false;
    private AccountViewModel accountViewModel;
    private BaseViewModel baseViewModel;
    private FragmentListBookingDetailsBinding binding;

    private List<Booking> bookingList = new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();
    private List<Cinema> cinemaList = new ArrayList<>();
    private List<CinemaHall> cinemaHallList = new ArrayList<>();
    private BookingAdapter bookingAdapter;

    private RecyclerView recyclerView;
    private String userId;

    public ListBookingDetailsFragment(boolean isExpired) {
        this.isExpired = isExpired;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
        baseViewModel = new ViewModelProvider(requireActivity()).get(BaseViewModel.class);

        binding = FragmentListBookingDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerview;
        bookingAdapter = new BookingAdapter(bookingList, movieList, cinemaList, cinemaHallList);
        recyclerView.setAdapter(bookingAdapter);

        bookingAdapter.setOnItemClickListener(position -> {
            Booking booking = bookingList.get(position);
            ViewUtils.createAskingDialog("Bạn chỉ có một lần sử dụng. Bạn có chắc chắn muốn sử dụng vé này không?",
                    requireContext(), v -> {
                        booking.setStatus(Booking.STATUS_USED);
                        baseViewModel.setSelectedBooking(booking);
                        new UpdateBookingController().updateBooking(booking);
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                        navController.navigate(R.id.action_nav_listBooking_to_nav_qr_code_of_booking);
                    }).show();
        });

        accountViewModel.getUserId().observe(getViewLifecycleOwner(), userId -> {
            this.userId = userId;
            updateUI();
        });

        return root;
    }

    private void updateUI() {
        if (userId == null) return;

        baseViewModel.getBookingList().observe(getViewLifecycleOwner(), bookings -> {
            List<Booking> filteredBookings = new ArrayList<>();
            for (Booking b : bookings) {
                if (b.getUserID().equals(userId)) {
                    if (isExpired && b.getStatus() != Booking.STATUS_AVAILABLE) {
                        filteredBookings.add(b);
                    } else if (!isExpired && b.getStatus() == Booking.STATUS_AVAILABLE) {
                        filteredBookings.add(b);
                    }
                }
            }

            baseViewModel.isDataReady().observe(getViewLifecycleOwner(), isReady -> {
                if (isReady) {
                    List<Movie> movies = baseViewModel.getMovieList().getValue();
                    List<Cinema> cinemas = baseViewModel.getCinemaList().getValue();
                    List<CinemaHall> cinemaHalls = baseViewModel.getCinemaHallList().getValue();

                    if (movies != null && cinemas != null && cinemaHalls != null) {
                        movieList.clear();
                        cinemaList.clear();
                        cinemaHallList.clear();

                        for (Booking b : filteredBookings) {
                            for (Movie m : movies) {
                                if (m.getId() == b.getShow().getMovieId()) {
                                    movieList.add(m);
                                    break;
                                }
                            }
                            for (Cinema c : cinemas) {
                                if (c.getId() == b.getShow().getCinemaId()) {
                                    cinemaList.add(c);
                                    break;
                                }
                            }
                            for (CinemaHall h : cinemaHalls) {
                                if (h.getId() == b.getShow().getHallId()) {
                                    cinemaHallList.add(h);
                                    break;
                                }
                            }
                        }

                        Log.e("ListBookingDetailsFragment", "updateUI: " + filteredBookings.size() + " " + movieList.size() + " " + cinemaList.size() + " " + cinemaHallList.size());
//                        bookingAdapter.updateBookings(filteredBookings);
                    }
                }
            });
        });
    }

}