package com.example.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.admin.R;
import com.example.admin.model.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User>  {
    private final Context context;
    private final List<User> users;

    public UserAdapter(@NonNull Context context, List<User> users) {
        super(context, 0, users);
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_user_layout, parent, false);
        }

        User user = users.get(position);

        ImageView imageProfile = view.findViewById(R.id.imageProfile);
        TextView userName = view.findViewById(R.id.userName);

//        Bitmap bitmap = decodeBitmap(user.getImage());
//        imageProfile.setImageBitmap(bitmap);
        userName.setText(user.getName());

        return view;
    }
}
