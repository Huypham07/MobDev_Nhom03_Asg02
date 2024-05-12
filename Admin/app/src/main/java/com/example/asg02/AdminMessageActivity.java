package com.example.asg02;

import static com.example.asg02.model.Event.encodeImage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.asg02.adapter.UserAdapter;
import com.example.asg02.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminMessageActivity extends BaseActivity {

    private ImageButton backBtn;
    private ListView userListView;
    private List<User> userList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message);

        backBtn = findViewById(R.id.backBtn7);
        userListView = findViewById(R.id.userListView);
        userList = new ArrayList<>();
        adapter = new UserAdapter(this, userList);
        userListView.setAdapter(adapter);
        userListView.setOnItemClickListener((adapterView, view, i, l) -> {
            User user = (User) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(this, AdminMessagingActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        handleReceivedUser();
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void handleReceivedUser() {
        //Do something

        //Test
        User user = new User();
        user.setName("Doanh");
        userList.add(user);
        User user2 = new User();
        user2.setName("LT Doanh");
        userList.add(user2);
        User user3 = new User();
        user3.setName("Thành Doanh");
        userList.add(user3);

        adapter.notifyDataSetChanged();
    }
}