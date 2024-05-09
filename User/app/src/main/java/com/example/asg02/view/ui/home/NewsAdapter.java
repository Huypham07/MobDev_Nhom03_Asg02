package com.example.asg02.view.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asg02.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    List<String> newsList;
    public NewsAdapter(List<String> newsList) {
        this.newsList = newsList;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public NewsHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.news_image);
        }
    }

    @NonNull
    @NotNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_recyclerview, parent, false);

        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.newsss);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
