package com.example.asg02;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.asg02.adapter.MessageAdapter;
import com.example.asg02.controller.message.GetAllMessageController;
import com.example.asg02.databinding.ActivityAdminMessagingBinding;
import com.example.asg02.model.Message;

import java.util.ArrayList;
import java.util.List;

public class AdminMessagingActivity extends BaseActivity {
    private ActivityAdminMessagingBinding binding;
    private String userId;
    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private GetAllMessageController messageController;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMessagingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userId = getIntent().getStringExtra("userId");
        if (userId == null) {
            throw new IllegalStateException("User id must not be null");
        }
        recyclerView = binding.messagesRecyclerView;
        adapter = new MessageAdapter(userId);
        adapter.submitList(messages);
        recyclerView.setAdapter(adapter);

        messageController = new GetAllMessageController();
        messageController.getMessages(userId
                , message -> {
                    messages.add(message);
                    recyclerView.scrollToPosition(messages.size() - 1);
                });

        binding.buttonSendMessage.setOnClickListener(
                v -> {
                    String text = binding.editTextMessage.getText().toString();
                    if (!text.isEmpty()) {
                        Message message = new Message("Admin", text, System.currentTimeMillis());
                        messageController.addMessage(message, userId);
                        binding.editTextMessage.setText("");
                    }
                }
        );

        binding.messagesRecyclerView.setOnTouchListener(
                (v, event) -> {
                    hideKeyboard();
                    return false;
                }
        );


        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    finish();
                }
        );

    }
    //Them ham de lay cac message tren firebase
}