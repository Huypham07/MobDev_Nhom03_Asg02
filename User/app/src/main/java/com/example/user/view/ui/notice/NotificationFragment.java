package com.example.user.view.ui.notice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.controller.notification.GetNotificationController;
import com.example.user.controller.notification.UpdateNotificationController;
import com.example.user.databinding.FragmentNotificationBinding;
import com.example.user.model.Notification;
import com.example.user.vm.AccountViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    private GetNotificationController controller;
    private AccountViewModel accountViewModel;
    private String userId;
    private RecyclerView recyclerView;
    private List<Notification> notifications = new ArrayList<>();
    private NotificationAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        accountViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        accountViewModel.getUserId().observe(
                getViewLifecycleOwner(),
                userId -> {
                    if (userId != null) {
                        this.userId = userId;
                        updateUI();
                    }
                }
        );

        recyclerView = binding.recyclerview;
        adapter = new NotificationAdapter(notifications);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Notification notification = notifications.get(position);
            notification.setStatus(Notification.STATUS_READ);
            new UpdateNotificationController().upadateNotification(notification);
            notifications.remove(position);
            adapter.notifyDataSetChanged();
            // do something
        });



        return root;
    }

    private void updateUI() {
        if (userId == null) {
            return;
        }

        notifications.clear();

        controller = new GetNotificationController();
        controller.getNotification(userId
                , notification -> {
                    if (notification.getStatus() == Notification.STATUS_UNREAD) {
                        if (notifications.contains(notification)) {
                            return;
                        }
                        notifications.add(0, notification);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}