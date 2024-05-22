package com.example.user.view.ui.event;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.databinding.FragmentEventsDetailsBinding;
import com.example.user.model.Event;
import com.example.user.utils.ImageUtils;
import com.example.user.vm.BaseViewModel;


public class EventDetailsFragment extends Fragment {
    private BaseViewModel baseViewModel;
    private FragmentEventsDetailsBinding binding;
    private Event event;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        baseViewModel = new ViewModelProvider(requireActivity()).get(BaseViewModel.class);

        binding = FragmentEventsDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        baseViewModel.getSelectedEvent().observe(
                getViewLifecycleOwner(),
                event -> {
                    this.event = event;
                    updateUI();
                }
        );

        return root;
    }

    private void updateUI() {
        if (event == null) {
            return;
        }
        binding.title.setText(event.getEventName());
        binding.duration.setText(event.getStartDate() + " - " + event.getEndDate());
        binding.description.setText(event.getEventInfo());
        if (event.getPoster() != null) {
            binding.eventPoster.setImageBitmap(ImageUtils.decodeBitmap(event.getPoster()));
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}