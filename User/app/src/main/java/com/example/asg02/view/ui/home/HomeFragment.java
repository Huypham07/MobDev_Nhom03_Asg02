package com.example.asg02.view.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.example.asg02.databinding.FragmentHomeBinding;
import com.example.asg02.view.ui.home.movieOverview.CustomFragmentAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TabLayoutMediator mediator;
    private CustomFragmentAdapter fragmentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentAdapter = new CustomFragmentAdapter(fragmentManager, getLifecycle());

        mediator = new TabLayoutMediator(binding.tabLayout, binding.viewPaper2, (tab, pos) -> {
           tab.setText(fragmentAdapter.getHeader(pos));
        });

        binding.viewPaper2.setAdapter(fragmentAdapter);
        binding.viewPaper2.setUserInputEnabled(false);

        if (!mediator.isAttached()) {
            mediator.attach();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}