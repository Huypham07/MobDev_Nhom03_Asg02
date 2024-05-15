package com.example.asg02.view.ui.booking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asg02.R;
import com.example.asg02.databinding.FragmentListBookingDetailsBinding;
import com.example.asg02.model.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListBookingDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListBookingDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "isExpired";

    // TODO: Rename and change types of parameters
    private boolean mParam;
    private FragmentListBookingDetailsBinding binding;

    public ListBookingDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter .
     * @return A new instance of fragment ListBookingDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListBookingDetailsFragment newInstance(boolean param) {
        ListBookingDetailsFragment fragment = new ListBookingDetailsFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getBoolean(ARG_PARAM);
        }
    }

    private List<Booking> bookingList;
    private BookingAdapter bookingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBookingDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (mParam) {
            if (bookingList == null) {
                bookingList = new ArrayList<>();
            }
        } else {
            if (bookingList == null) {
                bookingList = new ArrayList<>();
            }
        }

        bookingAdapter = new BookingAdapter(bookingList);
        binding.recyclerview.setAdapter(bookingAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerview);

        return root;
    }
}