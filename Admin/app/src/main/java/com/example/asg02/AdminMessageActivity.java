package com.example.asg02;

import static com.example.asg02.model.Event.encodeImage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.asg02.adapter.UserAdapter;
import com.example.asg02.controller.GetAccountController;
import com.example.asg02.model.User;

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