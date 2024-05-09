package com.example.asg02.view.ui.home.movieOverview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.asg02.model.Movie;
import org.jetbrains.annotations.NotNull;
import com.example.asg02.R;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    List<Movie> movieList = new ArrayList<>();

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView name;
        TextView duration;
        public MovieHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            name = itemView.findViewById(R.id.movie_name);
            duration = itemView.findViewById(R.id.movie_duration);
        }
    }

    @NonNull
    @NotNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.base_item_movie, parent, false);

        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieAdapter.MovieHolder holder, int position) {
        holder.poster.setImageResource(R.drawable.baby);
        holder.duration.setText(String.valueOf(movieList.get(position).getDurationMins()));
        holder.name.setText(String.valueOf(movieList.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
