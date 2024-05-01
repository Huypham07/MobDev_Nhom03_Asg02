package com.example.asg02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.asg02.databinding.FragmentCurrentMovieBinding;
import com.example.asg02.databinding.FragmentGalleryBinding;
import com.google.android.material.snackbar.Snackbar;

public class CurrentMovieFragment extends Fragment {

    private FragmentCurrentMovieBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCurrentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.tesst;
        textView.setOnClickListener(v -> {
            Snackbar.make(getView(), "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}