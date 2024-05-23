package com.example.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.model.Event;

public class AdminShowEventActivity extends BaseActivity {

    private Event event;

    private static final int REQUEST_CODE = 2004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_event);

        if (getIntent().hasExtra("event")) {
            event = (Event) getIntent().getSerializableExtra("event");
        }

        ImageButton backBtn = findViewById(R.id.backBtn6);
        ImageView posterImageView = findViewById(R.id.eventPosterImageView2);
        TextView nameTextView = findViewById(R.id.eventNameTextView2);
        TextView dateTextView = findViewById(R.id.eventDateTextView2);
        TextView infoTextView = findViewById(R.id.eventInfoTextView);
        ImageButton editBtn = findViewById(R.id.editBtn);

//        nameTextView.setText(event.getEventName());
//        Bitmap posterBitmap = Event.decodeBitmap(event.getPoster());
//        posterImageView.setImageBitmap(posterBitmap);
//        dateTextView.setText(event.getStartDate() + " - " + event.getEndDate());
//        infoTextView.setText(event.getEventInfo());

        if (event != null) {
            // Set event name
            String eventName = event.getEventName();
            if (eventName != null) {
                nameTextView.setText(eventName);
            } else {
                nameTextView.setText("");
            }

            // Set poster image
            Bitmap posterBitmap = null;
            if (posterBitmap != null) {
                posterBitmap = Event.decodeBitmap(event.getPoster());
            }
            posterImageView.setImageBitmap(posterBitmap);

            // Set date
            String startDate = event.getStartDate();
            String endDate = event.getEndDate();
            if (startDate != null && endDate != null) {
                dateTextView.setText(startDate + " - " + endDate);
            } else {
                dateTextView.setText("");
            }

            String eventInfo = event.getEventInfo();
            if (eventInfo != null) {
                infoTextView.setText(eventInfo);
            } else {
                infoTextView.setText("");
            }
        } else {
            nameTextView.setText("");
            posterImageView.setImageBitmap(null); // Clear image
            dateTextView.setText("");
            infoTextView.setText("");
        }


        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminManageEventActivity.class);
            startActivity(intent);
        });

        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminAddEventActivity.class);
            intent.putExtra("event", event);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }
}