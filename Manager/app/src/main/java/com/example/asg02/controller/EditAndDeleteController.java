package com.example.asg02.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.asg02.model.Show;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditAndDeleteController {
    private FirebaseDatabase database;
    private Context context;
    public EditAndDeleteController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    public void delete(Integer id, String type) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(type);

        // Tìm và xóa phần tử có trường "id" bằng giá trị id truyền vào
        Query query = ref.orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Xóa phần tử
                        snapshot.getRef().removeValue(); // hoặc snapshot.getRef().setValue(null);
                    }
                } else {
                    Log.d("Firebase", "Không tìm thấy phần tử với id = " + id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Lỗi đọc dữ liệu:", databaseError.toException());
            }
        });
    }

}
