package com.example.asg02.view.ui.movie;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asg02.R;
import com.example.asg02.databinding.FragmentMovieBinding;
import com.example.asg02.databinding.FragmentNotificationBinding;
import com.example.asg02.view.ui.notice.NotificationViewModel;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;
    private MovieViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        binding = FragmentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}