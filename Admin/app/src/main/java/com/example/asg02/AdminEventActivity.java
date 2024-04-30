package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminEventActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private FrameLayout addNewEventBtn;

    private ListView eventListView;
    private ArrayList<Event> eventList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);

        backBtn = findViewById(R.id.backBtn4);
        addNewEventBtn = findViewById(R.id.addNewEventBtn);
        eventListView = findViewById(R.id.eventListView);
        eventList = new ArrayList<>();
        adapter = new CustomAdapter(this, eventList);
        eventListView.setAdapter(adapter);

        handleReceivedEvent();

        addNewEventBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminAddEventActivity.class);
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
        });
    }

    private void handleReceivedEvent() {
        // Thêm chức năng lấy danh sách các sự kiện ở đây

        if (eventList.isEmpty()) {
            // Tạo một sự kiện mặc định
            Event defaultEvent = new Event("Tên sự kiện mặc định", "Đường dẫn hình ảnh mặc định", "Ngày bắt đầu", "Ngày kết thúc", "Thông tin sự kiện");

            // Thêm sự kiện mặc định vào danh sách
            eventList.add(defaultEvent);
        }

        // Cập nhật adapter
        adapter.notifyDataSetChanged();
    }
}