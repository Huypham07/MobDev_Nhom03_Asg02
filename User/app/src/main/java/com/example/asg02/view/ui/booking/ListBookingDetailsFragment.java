package com.example.asg02.view.ui.booking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asg02.R;
import com.example.asg02.controller.booking.GetBookingController;
import com.example.asg02.databinding.FragmentListBookingDetailsBinding;
import com.example.asg02.model.Booking;
import com.example.asg02.view.Utils;

import java.util.ArrayList;
import java.util.List;

public class ListBookingDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "isExpired";

    // TODO: Rename and change types of parameters
    private boolean isExpired = true;
    private FragmentListBookingDetailsBinding binding;

    private List<Booking> bookingList = new ArrayList<>();
    private BookingAdapter bookingAdapter;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBookingDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            isExpired = getArguments().getBoolean("isExpired");
        }
        recyclerView = binding.recyclerview;
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);

        String userId = getActivity().getIntent().getStringExtra("userId");

        new GetBookingController().getAllBookings(userId).thenAccept(bookings -> {
           if (bookings != null) {
               bookingList.clear();
               for (Booking b : bookings) {
                   if (isExpired) {
                       if  (Utils.isCurrentMovie(b.getShow().getDate())) {
                           bookingList.add(b);
                       }
                   } else {
                       if  (!Utils.isCurrentMovie(b.getShow().getDate())) {
                           bookingList.add(b);
                       }
                   }
               }

               for (Booking b : bookingList) {
                   Log.d("Booking", String.valueOf(b.getShow().getMovieId()));
               }
               bookingAdapter.notifyDataSetChanged();
               bookingAdapter = new BookingAdapter(bookingList);
               recyclerView.setAdapter(bookingAdapter);
           }
        });

        return root;
    }
}