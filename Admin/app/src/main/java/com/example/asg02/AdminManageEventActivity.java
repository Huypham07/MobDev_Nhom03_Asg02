package com.example.asg02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.asg02.adapter.EventAdapter;
import com.example.asg02.controller.GetEventController;
import com.example.asg02.model.Event;

import java.util.ArrayList;
import java.util.List;

public class AdminManageEventActivity extends BaseActivity {

    private ImageButton backBtn;
    private FrameLayout addNewEventBtn;

    private ListView eventListView;
    private List<Event> eventList;
    private EventAdapter adapter;

    private GetEventController getEventsController;

    @Override
    protected void onResume() {
        super.onResume();
        handleReceivedEvent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);

        backBtn = findViewById(R.id.backBtn4);
        addNewEventBtn = findViewById(R.id.addNewEventBtn);
        eventListView = findViewById(R.id.eventListView);
        eventList = new ArrayList<>();
        adapter = new EventAdapter(this, eventList);
        eventListView.setAdapter(adapter);
        getEventsController = new GetEventController();

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
        getEventsController.getAllEvent().thenApply(events -> {
            if (!events.isEmpty()) {
                eventList.clear();
                eventList.addAll(events);
                adapter.notifyDataSetChanged();
            }
            return null;
        });
    }
}