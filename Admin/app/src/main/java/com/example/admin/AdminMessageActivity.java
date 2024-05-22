package com.example.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.admin.adapter.UserAdapter;
import com.example.admin.controller.GetAccountController;
import com.example.admin.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AdminMessageActivity extends BaseActivity {

    private ImageButton backBtn;
    private ListView userListView;
    private List<User> userList;
    private List<String> userIdList;
    private UserAdapter adapter;
    private GetAccountController accountController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message);

        backBtn = findViewById(R.id.backBtn7);
        userListView = findViewById(R.id.userListView);
        userList = new ArrayList<>();
        userIdList = new ArrayList<>();
        adapter = new UserAdapter(this, userList);
        userListView.setAdapter(adapter);
        userListView.setOnItemClickListener((adapterView, view, i, l) -> {
            User user = (User) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(this, AdminMessagingActivity.class);
            intent.putExtra("userId", userIdList.get(i));
            intent.putExtra("user", user);
            startActivity(intent);
        });

        accountController = new GetAccountController();

        handleReceivedUser();
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void handleReceivedUser() {
        //Do something
        accountController.getAllUser(new Consumer<Map.Entry<String, User>>() {
            @Override
            public void accept(Map.Entry<String, User> stringUserEntry) {
                User user = stringUserEntry.getValue();
                String userId = stringUserEntry.getKey();
                if (user != null) {
                    userList.add(user);
                    userIdList.add(userId);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}