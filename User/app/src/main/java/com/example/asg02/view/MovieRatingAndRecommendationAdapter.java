package com.example.asg02.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asg02.R;
import com.example.asg02.model.Movie;

import java.util.List;

public class MovieRatingAndRecommendationAdapter extends RecyclerView.Adapter<MovieRatingAndRecommendationAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieRatingAndRecommendationAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieTitle.setText(movie.getName());
        holder.movieDuration.setText(String.format("%d mins", movie.getDurationMins()));

        // Decode the movie poster string to Bitmap and set it to ImageView
        Bitmap bitmap = decodeBitmap(movie.getPoster());
        holder.moviePoster.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView movieTitle;
        TextView movieDuration;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_rating_recommendation_poster);
            movieTitle = itemView.findViewById(R.id.movie_rating_recommendation_title);
            movieDuration = itemView.findViewById(R.id.movie_rating_recommendation_duration);
        }
    }

    // Decode Bitmap from encoded string
    public static Bitmap decodeBitmap(String uri) {
        byte[] bytes = java.util.Base64.getDecoder().decode(uri);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
