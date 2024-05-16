package com.example.asg02.view.ui.booking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asg02.R;
import com.example.asg02.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingHolder> {
    private List<Booking> bookingList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public class BookingHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView duration;
        TextView expiredDate;
        CardView cardView;
        public BookingHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
            name = itemView.findViewById(R.id.movie_name);
            duration = itemView.findViewById(R.id.movie_duration);
            expiredDate = itemView.findViewById(R.id.expired_date);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);

        return new BookingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolder holder, int position) {
        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
        //set data
    }

    @Override
    public int getItemCount() {
        return this.bookingList.size();
    }
}