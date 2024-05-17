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
import com.example.asg02.databinding.FragmentHomeBinding;
import com.example.asg02.model.Event;
import com.example.asg02.view.ui.home.movieOverview.CustomFragmentAdapter;
import com.example.asg02.vm.BaseViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private BaseViewModel baseViewModel;
    private FragmentHomeBinding binding;
    private TabLayoutMediator mediator;
    private CustomFragmentAdapter fragmentAdapter;
    private EventAdapter eventAdapter;
    private RecyclerView recyclerView;
    private List<Event> eventList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        baseViewModel = new ViewModelProvider(requireActivity()).get(BaseViewModel.class);
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

        eventAdapter = new EventAdapter(eventList);
        recyclerView = binding.recyclerview;
        recyclerView.setAdapter(eventAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        eventAdapter.setOnItemClickListener(position -> {
            baseViewModel.setSelectedEvent(eventList.get(position));
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            controller.navigate(R.id.nav_events_detail);
        });

        baseViewModel.getEventList().observe(
            getViewLifecycleOwner(),
            events -> {
                eventList.clear();
                eventList.addAll(events);
                eventAdapter.notifyDataSetChanged();
            }

        );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}