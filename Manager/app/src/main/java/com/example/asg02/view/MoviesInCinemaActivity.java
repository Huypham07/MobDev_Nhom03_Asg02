package com.example.asg02.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asg02.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MoviesInCinemaActivity extends AppCompatActivity {
    ImageButton back;
    final long ONE_MEGABYTE = 2 * 1024 * 1024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_in_cinema);
        back = findViewById(R.id.backIn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoviesInCinemaActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        ArrayList<ImageView> imageViews = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference moviesRef = rootRef.child("Movies");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        moviesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                    String title = movieSnapshot.child("Title").getValue(String.class);
                    StorageReference imageRef = storageRef.child("posters/"
                            + title.replace(" ","").replace(":","")
                            + "_poster.png");
                    imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        ImageView imageView = new ImageView(MoviesInCinemaActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                250 * 2 /* Chiều rộng 250dp */,
                                300 * 3/* Chiều cao 300dp */);
                        layoutParams.weight = 1;
                        imageView.setLayoutParams(layoutParams);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // Điều chỉnh tỉ lệ hiển thị nếu cần
                        imageView.setImageBitmap(bitmap);
                        LinearLayout linearLayout = findViewById(R.id.showMovies);
                        linearLayout.addView(imageView);
                        imageViews.add(imageView);
                    }).addOnFailureListener(exception -> {
                        Toast.makeText(MoviesInCinemaActivity.this, "posters/"
                                + title.replace(" ","").replace(":","")
                                + "_poster.png", Toast.LENGTH_SHORT).show();
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
                System.out.println("Error retrieving data: " + databaseError.getMessage());
            }
        });
    }
}