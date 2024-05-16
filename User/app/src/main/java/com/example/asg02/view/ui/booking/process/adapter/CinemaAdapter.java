package com.example.asg02.view.ui.booking.process.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.asg02.R;
import com.example.asg02.controller.show.GetShowController;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.Show;

import java.util.ArrayList;
import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.CinemaHolder> {

    private List<Cinema> cinemas;
    private List<List<Show>> shows = new ArrayList<>();
    private List<ShowAdapter> showAdapters = new ArrayList<>();
    private int movieId;
    private String date;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int cinemaPos, int showPos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public List<ShowAdapter> getShowAdapters() {
        return showAdapters;
    }

    public CinemaAdapter(List<Cinema> cinemas, int movieId, String date) {
        this.cinemas = cinemas;
        this.movieId = movieId;
        this.date = date;
    }

    public class CinemaHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView cinemaName;
        RecyclerView showLayout;
        public CinemaHolder(View itemView) {
            super(itemView);
            cinemaName = itemView.findViewById(R.id.name_cinema);
            layout = itemView.findViewById(R.id.cinema_layout);
            showLayout = itemView.findViewById(R.id.show_layout);
        }
    }

    @NonNull
    @Override
    public CinemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cinema_and_shows, parent, false);

        return new CinemaHolder(itemView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CinemaHolder holder, int position) {
        shows.clear();
        showAdapters.clear();
        for (int i = 0; i < cinemas.size(); i++) {
            shows.add(new ArrayList<>());
            showAdapters.add(new ShowAdapter(shows.get(i)));
        }
        String name = cinemas.get(position).getName();
        holder.cinemaName.setText(name);
        ShowAdapter showAdt = showAdapters.get(position);
        holder.showLayout.setAdapter(showAdt);
        showAdt.setOnItemClickListener(showPos -> {
            if (listener != null) {
                listener.onItemClick(position, showPos);
            }
        });

        new GetShowController().getAllShowsByCinemaAndMovie(cinemas.get(position).getId(), movieId, date).thenAccept(shows -> {
            if (shows != null) {
                if (shows != null) {
                    this.shows.get(position).clear();
                    this.shows.get(position).addAll(shows);
                    showAdt.notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return cinemas.size();
    }
}
