package com.example.admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.admin.AdminShowEventActivity;
import com.example.admin.R;
import com.example.admin.controller.DeleteEventController;
import com.example.admin.model.Event;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final List<Event> events;

    public EventAdapter(Context context, List<Event> events) {
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

        TextView nameTextView = view.findViewById(R.id.eventNameTextView);
        ImageView posterImageView = view.findViewById(R.id.eventPosterImageView);
        TextView dateTextView = view.findViewById(R.id.eventDateTextView);

        FrameLayout deleteEventBtn = view.findViewById(R.id.deleteEventBtn);
        ConstraintLayout showEventBtn = view.findViewById(R.id.showEventBtn);

        deleteEventBtn.setOnClickListener(v -> {
            events.remove(event);
            if (deleteEventController.deleteEvent(event)) {
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

        nameTextView.setText(event.getEventName());
        Bitmap posterBitmap = Event.decodeBitmap(event.getPoster());
        posterImageView.setImageBitmap(posterBitmap);
        dateTextView.setText(event.getStartDate() + " - " + event.getEndDate());

        return view;
    }
}
