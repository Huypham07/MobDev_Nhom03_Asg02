package com.example.user.view.ui.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.model.Movie;
import com.example.user.utils.DateTimeUtils;
import com.example.user.utils.ImageUtils;

import java.util.List;

public class MovieRatingAndRecommendationAdapter extends RecyclerView.Adapter<MovieRatingAndRecommendationAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MovieRatingAndRecommendationAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_horizontal, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.moviePoster.setImageBitmap(ImageUtils.decodeBitmap(movie.getPoster()));
        holder.movieName.setText(movie.getName());
        holder.movieCensor.setText(movie.getCensor());
        holder.movieDuration.setText(DateTimeUtils.convertMinsToStringTime(movie.getDurationMins()));
        holder.layout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public void updateMovieList(List<Movie> newMovieList) {
        this.movieList = newMovieList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView movieName;
        TextView movieCensor;
        TextView movieDuration;
        LinearLayout layout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            movieName = itemView.findViewById(R.id.movie_name);
            movieCensor = itemView.findViewById(R.id.movie_censor);
            movieDuration = itemView.findViewById(R.id.movie_duration);
            layout = itemView.findViewById(R.id.layout);
        }
    }

}
