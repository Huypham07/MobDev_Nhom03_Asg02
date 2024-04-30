package com.example.asg02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asg02.Event;
import com.example.asg02.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Event> {

    private Context context;
    private ArrayList<Event> events;

    public CustomAdapter(Context context, ArrayList<Event> events) {
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

        Event event = events.get(position);

        ImageView posterImageView = view.findViewById(R.id.eventPosterImageView);
        TextView nameTextView = view.findViewById(R.id.eventNameTextView);
        TextView dateTextView = view.findViewById(R.id.eventDateTextView);

        // Hiển thị poster và tên sự kiện
//         posterImageView.setImageBitmap(event.getPoster());
         nameTextView.setText(event.getEventName());
         posterImageView.setImageResource(R.drawable.poster_test);
         dateTextView.setText(event.getStartDate() + " - " + event.getEndDate());

        return view;
    }
}
