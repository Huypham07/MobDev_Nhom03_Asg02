package com.example.asg02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asg02.R;
import com.example.asg02.model.Message;
import com.example.asg02.model.User;

import java.util.List;

public class MessageAdapter extends ArrayAdapter {

    private final Context context;
    private final List<Message> messages;

    public MessageAdapter(@NonNull Context context, List<Message> messages) {
        super(context, 0, messages);
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_message_layout, parent, false);
        }

        Message message = messages.get(position);

        TextView messageTextView = view.findViewById(R.id.messageTextView);
        messageTextView.setText(message.getMessage());
        return view;
    }
}
