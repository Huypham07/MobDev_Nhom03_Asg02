package com.example.asg02.view.ui.booking.process;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asg02.R;
import com.example.asg02.databinding.FragmentChooseCinemaBinding;
import com.example.asg02.model.Manager;
import com.example.asg02.model.Movie;

public class ChooseCinemaFragment extends Fragment {
    private static final String ARG_PARAM1 = "manager";
    private Manager manager;
    private static final String ARG_PARAM2 = "manager";
    private Movie movie;
    private FragmentChooseCinemaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChooseCinemaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        manager = (Manager) getArguments().getSerializable(ARG_PARAM1);
        movie = (Movie) getArguments().getSerializable(ARG_PARAM2);

        if (manager != null) {
            // do something with manager
//            new
        }

        return root;
    }
}