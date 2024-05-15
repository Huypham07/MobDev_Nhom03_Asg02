package com.example.asg02.view.ui.event;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asg02.databinding.FragmentEventsDetailsBinding;
import com.example.asg02.model.Event;
import com.example.asg02.view.Utils;


public class EventDetailsFragment extends Fragment {
    private FragmentEventsDetailsBinding binding;
    private static final String ARG_PARAM = "event";
    private Event event;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEventsDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        event = (Event) getArguments().getSerializable(ARG_PARAM);
        if (event != null) {
            binding.title.setText(event.getEventName());
            binding.duration.setText(event.getStartDate() + " - " + event.getEndDate());
            binding.description.setText(event.getEventInfo());
            if (event.getPoster() != null) {
                binding.eventPoster.setImageBitmap(Utils.decodeBitmap(event.getPoster()));
            }
        }

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}