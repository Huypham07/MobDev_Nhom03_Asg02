package com.example.asg02;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.asg02.controller.DeleteEventController;
import com.example.asg02.model.Event;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final List<Event> events;

    public CustomAdapter(Context context, List<Event> events) {
        super(context, 0, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_event_layout, parent, false);
        }

        DeleteEventController deleteEventController = new DeleteEventController();

        Event event = events.get(position);

        ImageView posterImageView = view.findViewById(R.id.eventPosterImageView);
        TextView nameTextView = view.findViewById(R.id.eventNameTextView);
        TextView dateTextView = view.findViewById(R.id.eventDateTextView);

        FrameLayout deleteEventBtn = view.findViewById(R.id.deleteEventBtn);
        ConstraintLayout showEventBtn = view.findViewById(R.id.showEventBtn);

        deleteEventBtn.setOnClickListener(v -> {
            if (deleteEventController.deleteEvent(event)) {
                events.remove(event);
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_LONG);
            }
            notifyDataSetChanged();
        });

        showEventBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, AdminShowEventActivity.class);
            intent.putExtra("event", event);
            context.startActivity(intent);
        });

//         posterImageView.setImageBitmap(event.getPoster());
         nameTextView.setText(event.getEventName());
         posterImageView.setImageResource(R.drawable.poster_test);
         dateTextView.setText(event.getStartDate() + " - " + event.getEndDate());

        return view;
    }
}
