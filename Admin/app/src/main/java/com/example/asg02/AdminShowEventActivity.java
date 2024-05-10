package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asg02.model.Event;

public class AdminShowEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_event);

        Event event = (Event) getIntent().getSerializableExtra("event");

        ImageButton backBtn = findViewById(R.id.backBtn6);
        ImageView posterImageView = findViewById(R.id.eventPosterImageView2);
        TextView nameTextView = findViewById(R.id.eventNameTextView2);
        TextView dateTextView = findViewById(R.id.eventDateTextView2);
        TextView infoTextView = findViewById(R.id.eventInfoTextView);

        nameTextView.setText(event.getEventName());
        posterImageView.setImageResource(R.drawable.poster_test);
        dateTextView.setText(event.getStartDate() + " - " + event.getEndDate());
        infoTextView.setText(event.getEventInfo());

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminManageEventActivity.class);
            startActivity(intent);
        });
    }
}