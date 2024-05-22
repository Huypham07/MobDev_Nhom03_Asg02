package com.example.admin;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.admin.adapter.MessageAdapter;
import com.example.admin.controller.message.GetAllMessageController;
import com.example.admin.databinding.ActivityAdminMessagingBinding;
import com.example.admin.model.Message;
import com.example.admin.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminMessagingActivity extends BaseActivity {
    private ActivityAdminMessagingBinding binding;
    private String userId;
    private User user;
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
        user = (User) getIntent().getSerializableExtra("user");
        if (userId == null) {
            throw new IllegalStateException("User id must not be null");
        }

        if (user == null) {
            throw new IllegalStateException("User must not be null");
        }

        binding.toolbar.setTitle(user.getName());
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