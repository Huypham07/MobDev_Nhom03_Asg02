package com.example.asg02.view.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.asg02.R;
import com.example.asg02.controller.GetNewsController;
import com.example.asg02.databinding.FragmentHomeBinding;
import com.example.asg02.view.ui.home.movieOverview.CustomFragmentAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TabLayoutMediator mediator;
    private CustomFragmentAdapter fragmentAdapter;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private List<String> newsList;
    private GetNewsController getNewsController;

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

        getNewsController = new GetNewsController();
        newsList = getNewsController.getAllNews();
        newsAdapter = new NewsAdapter(newsList);
        newsAdapter.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            controller.navigate(R.id.nav_news_detail);
        });
        recyclerView = binding.recyclerview;
        recyclerView.setAdapter(newsAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}