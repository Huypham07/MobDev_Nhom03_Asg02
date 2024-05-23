package com.example.user.view.ui.notice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.model.Notification;
import com.example.user.utils.DateTimeUtils;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> notifications;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView timestamp;
        LinearLayout layout;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            timestamp = itemView.findViewById(R.id.time_stamp);
            layout = itemView.findViewById(R.id.layout);
        }
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.content.setText(notifications.get(position).getContent());
        holder.timestamp.setText(DateTimeUtils.convertTimestampToDate(notifications.get(position).getTimestamp()));
        holder.layout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
