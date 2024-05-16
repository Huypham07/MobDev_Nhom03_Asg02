package com.example.asg02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asg02.controller.EditAndDeleteController;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.Hall;
import com.example.asg02.model.Show;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerEditAndDeleteActivity extends AppCompatActivity {
    ImageButton back;
    TextView showInforCinemaHallShowTextView, confirmCinema, cancelCinema,
    confirmHall, cancelHall, confirmShow, cancelShow ;
    Spinner chooseCinemaSpinner, chooseHallSpinner, chooseShowSpinner,
            chooseMovieSpinnerInShowDialog, chooseHallSpinnerInShowDialog;
    Button editButton, deleteButton;
    Dialog cinemaDialog, hallDialog, showDialog;
    EditText nameInCinemaDialog, provinceInCinemaDialog, districtInCinemaDialog,
            communeInCinemaDialog, detailAddressInCinemaDialog,
            nameInHallDialog, seatsPerRowInHallDialog, seatsPerColumnInHallDialog,
            startTimeInShowDialog, endTimeInShowDialog, dateInShowDialog;
    List<String> cinemaNamesList;
    List<Cinema> cinemaList;
    List<String> hallNamesList;
    List<Hall> hallList;
    List<String> showTimeList;
    List<Show> showList;
    Integer finalCinemaId;
    Integer finalHallId;
    Integer finalShowId;
    Integer hallIdInShowDialog, movieIdInShowDialog;
    Integer deleteId;
    String deleteType;
    EditAndDeleteController editAndDeleteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_edit_and_delete);
        back = findViewById(R.id.backEditAndDelete);
        chooseCinemaSpinner = findViewById(R.id.chooseCinemaInEditAndDelete);
        chooseHallSpinner = findViewById(R.id.chooseHallInEditAndDelete);
        chooseShowSpinner = findViewById(R.id.chooseShowInEditAndDelete);
        showInforCinemaHallShowTextView = findViewById(R.id.showInfoOfCinemaHallShow);
        editButton = findViewById(R.id.edit);
        deleteButton = findViewById(R.id.delete);
        cinemaNamesList = new ArrayList<>();
        cinemaList = new ArrayList<>();
        hallNamesList = new ArrayList<>();
        hallList = new ArrayList<>();
        showTimeList = new ArrayList<>();
        showList = new ArrayList<>();
        cinemaNamesList.add("Choose a cinema");
        hallNamesList.add("Choose a hall");
        showTimeList.add("Choose a show");
        editAndDeleteController = new EditAndDeleteController(this);
        cinemaDialog = new Dialog(ManagerEditAndDeleteActivity.this);
        hallDialog = new Dialog(ManagerEditAndDeleteActivity.this);
        showDialog = new Dialog(ManagerEditAndDeleteActivity.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerEditAndDeleteActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("Cinemas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot cinemaSnapshot : dataSnapshot.getChildren()) {
                        // Extract cinema name
                        Integer id = cinemaSnapshot.child("id").getValue(Integer.class);
                        String name = cinemaSnapshot.child("name").getValue(String.class);
                        String province = cinemaSnapshot.child("province").getValue(String.class);
                        String district = cinemaSnapshot.child("district").getValue(String.class);
                        String commune = cinemaSnapshot.child("commune").getValue(String.class);
                        String detailAddress = cinemaSnapshot.child("detailAddress").getValue(String.class);
                        String manager = cinemaSnapshot.child("manager").getValue(String.class);
                        Cinema cinema = new Cinema(id.intValue(), name, province, district, commune, detailAddress, manager);
                        cinemaNamesList.add(name);
                        cinemaList.add(cinema);
                    }
                    // Create an ArrayAdapter and set it to the Spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, cinemaNamesList);
                    chooseCinemaSpinner.setAdapter(adapter);
                } else {
                    // Handle the case where "Cinemas" node doesn't exist
                    Log.d("Firebase", "Cinemas node does not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error reading data:", databaseError.toException());
            }
        });
        chooseCinemaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    finalCinemaId = (Integer) cinemaList.get(position - 1).getId();
                    deleteId = finalCinemaId;
                    deleteType = "Cinemas";
                    showInforCinemaHallShowTextView.setText("Cinema info: "
                            + "\nname: " + cinemaList.get(position - 1).getName()
                            + "\nprovince: " + cinemaList.get(position - 1).getProvince()
                            + "\ndistrict: " + cinemaList.get(position - 1).getDistrict()
                            + "\ncommune: " + cinemaList.get(position - 1).getCommune()
                            + "\ndetailAddress: " + cinemaList.get(position - 1).getDetailAddress());
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    database.getReference("Halls").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                hallNamesList.clear();
                                hallList.clear();
                                showTimeList.clear();
                                showList.clear();
                                hallNamesList.add("Choose a Hall");
                                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, showTimeList);
                                chooseShowSpinner.setAdapter(adapter1);
                                for (DataSnapshot hallSnapshot : dataSnapshot.getChildren()) {
                                    // Extract cinema name
                                    Integer cinemaIdInHall = hallSnapshot.child("cinemaId").getValue(Integer.class);
                                    if (cinemaIdInHall.equals(finalCinemaId) ) {
                                        String name = hallSnapshot.child("name").getValue(String.class);
                                        Integer id = hallSnapshot.child("id").getValue(Integer.class);
                                        Integer seatsPerRow = hallSnapshot.child("seatsPerRow").getValue(Integer.class);
                                        Integer seatsPerColumn = hallSnapshot.child("seatsPerColumn").getValue(Integer.class);
                                        Integer cinemaId = hallSnapshot.child("cinemaId").getValue(Integer.class);
                                        hallNamesList.add(name);
                                        hallList.add(new Hall(id.intValue(), name, seatsPerRow.intValue(), seatsPerColumn.intValue(), cinemaId.intValue()));
                                    }
                                    else {
                                        Toast.makeText(ManagerEditAndDeleteActivity.this, "Error", Toast.LENGTH_SHORT);
                                    }
                                }
                                // Create an ArrayAdapter and set it to the Spinner
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, hallNamesList);
                                chooseHallSpinner.setAdapter(adapter);
                            } else {
                                // Handle the case where "Cinemas" node doesn't exist
                                Log.d("Firebase", "Cinemas node does not exist");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("Firebase", "Error reading data:", databaseError.toException());
                        }
                    });
                }
                // Get the selected cinema name from the Spinner
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        chooseHallSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected cinema name from the Spinner
                if (position > 0) {
                    finalHallId = (Integer) hallList.get(position - 1).getId();
                    deleteId = finalHallId;
                    deleteType = "Halls";
                    showInforCinemaHallShowTextView.setText("Hall info: "
                            + "\nname: " + hallList.get(position - 1).getName()
                            + "\nseatsPerRow: " + hallList.get(position - 1).getSeatsPerRow()
                            + "\nseatsPerColumn: " + hallList.get(position - 1).getSeatsPerColumn());
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    database.getReference("Shows").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                showTimeList.clear();
                                showList.clear();
                                showTimeList.add("Choose a show");
                                for (DataSnapshot showSnapshot : dataSnapshot.getChildren()) {
                                    // Extract cinema name
                                    Integer hallIdInShow = showSnapshot.child("hallId").getValue(Integer.class);
                                    if (hallIdInShow.equals(finalHallId) ) {
                                        Integer id = showSnapshot.child("id").getValue(Integer.class);
                                        Integer cinemaId = showSnapshot.child("cinemaId").getValue(Integer.class);
                                        Integer hallId = showSnapshot.child("hallId").getValue(Integer.class);
                                        Integer movieId = showSnapshot.child("movieId").getValue(Integer.class);
                                        String startTime = showSnapshot.child("startTime").getValue(String.class);
                                        String endTime = showSnapshot.child("endTime").getValue(String.class);
                                        String date = showSnapshot.child("date").getValue(String.class);
                                        showTimeList.add(startTime + ":" + endTime + " " + date);
                                        showList.add(new Show(id.intValue(), cinemaId.intValue(), hallId.intValue(), movieId.intValue(), startTime, endTime, date));
                                    }
                                    else {
                                        Toast.makeText(ManagerEditAndDeleteActivity.this, "Error", Toast.LENGTH_SHORT);
                                    }
                                }
                                // Create an ArrayAdapter and set it to the Spinner
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, showTimeList);
                                chooseShowSpinner.setAdapter(adapter);
                            } else {
                                // Handle the case where "Cinemas" node doesn't exist
                                Log.d("Firebase", "Cinemas node does not exist");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("Firebase", "Error reading data:", databaseError.toException());
                        }
                    });
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        chooseShowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    finalShowId = showList.get(position - 1).getId();
                    deleteId = finalShowId;
                    deleteType = "Shows";
                    showInforCinemaHallShowTextView.setText("Show info: "
                            + "\nstartTime: " + showList.get(position - 1).getStartTime()
                            + "\nendTime: " + showList.get(position - 1).getEndTime()
                            + "\ndate: " + showList.get(position - 1).getDate()
                            + "\nmovieId: " + showList.get(position - 1).getMovieId()
                            + "\nhallId: " + showList.get(position - 1).getHallId());
                }
                // Get the selected cinema name from the Spinner

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAndDeleteController.delete(deleteId, deleteType);
                Intent intent = new Intent(ManagerEditAndDeleteActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteType.equals("Cinemas")) {
                    cinemaDialog.setContentView(R.layout.dialog_cinema);
                    cinemaDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    cinemaDialog.setCancelable(false);
                    cinemaDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    confirmCinema = cinemaDialog.findViewById(R.id.confirmCinema);
                    cancelCinema = cinemaDialog.findViewById(R.id.cancelCinema);
                    nameInCinemaDialog = cinemaDialog.findViewById(R.id.nameInCinemaDialog);
                    provinceInCinemaDialog = cinemaDialog.findViewById(R.id.provinceInCinemaDialog);
                    districtInCinemaDialog = cinemaDialog.findViewById(R.id.districtInCinemaDialog);
                    communeInCinemaDialog = cinemaDialog.findViewById(R.id.communeInCinemaDialog);
                    detailAddressInCinemaDialog = cinemaDialog.findViewById(R.id.detailAddressInCinemaDialog);
                    List<String> cinemaNamesListInHallDialog = new ArrayList<>();
                    List<Integer> cinemaIdListInHallDialog = new ArrayList<>();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    database.getReference("Cinemas").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot cinemaSnapshot : dataSnapshot.getChildren()) {
                                    // Extract cinema name
                                    Integer cinemaId = cinemaSnapshot.child("id").getValue(Integer.class);
                                    if (deleteId.equals(cinemaId) ) {
                                        nameInCinemaDialog.setText(cinemaSnapshot.child("name").getValue(String.class));
                                        provinceInCinemaDialog.setText(cinemaSnapshot.child("province").getValue(String.class));
                                        districtInCinemaDialog.setText(cinemaSnapshot.child("district").getValue(String.class));
                                        communeInCinemaDialog.setText(cinemaSnapshot.child("commune").getValue(String.class));
                                        detailAddressInCinemaDialog.setText(cinemaSnapshot.child("detailAddress").getValue(String.class));
                                    }
                                }
                            } else {
                                // Handle the case where "Cinemas" node doesn't exist
                                Log.d("Firebase", "Cinemas node does not exist");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("Firebase", "Error reading data:", databaseError.toException());
                        }
                    });
                    confirmCinema.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatabaseReference cinemasRef = database.getReference("Cinemas");
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("name", nameInCinemaDialog.getText().toString());
                            updates.put("province", provinceInCinemaDialog.getText().toString());
                            updates.put("district", districtInCinemaDialog.getText().toString());
                            updates.put("commune", communeInCinemaDialog.getText().toString());
                            updates.put("detailAddress", detailAddressInCinemaDialog.getText().toString());
                            // Đường dẫn đến nút của bộ phim cần cập nhật
                            DatabaseReference cinemaToUpdateRef = cinemasRef.child(deleteId.toString());
                            // Thực hiện cập nhật
                            cinemaToUpdateRef.updateChildren(updates)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Xử lý thành công
                                            Log.d("CinemaUpdater", "Cinema updated successfully");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Xử lý khi thất bại
                                            Log.e("CinemaUpdater", "Error updating cinema", e);
                                        }
                                    });
                            cinemaDialog.dismiss();
                            Toast.makeText(ManagerEditAndDeleteActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
                        }
                    });

                    cancelCinema.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cinemaDialog.dismiss();
                            Toast.makeText(ManagerEditAndDeleteActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                    cinemaDialog.show();
                } else if (deleteType.equals("Halls")) {
                    hallDialog.setContentView(R.layout.dialog_hall);
                    hallDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    hallDialog.setCancelable(false);
                    hallDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    confirmHall = hallDialog.findViewById(R.id.confirmHall);
                    cancelHall = hallDialog.findViewById(R.id.cancelHall);
                    nameInHallDialog = hallDialog.findViewById(R.id.nameInHallDialog);
                    seatsPerRowInHallDialog = hallDialog.findViewById(R.id.seatsPerRowInHallDialog);
                    seatsPerColumnInHallDialog = hallDialog.findViewById(R.id.seatsPerColumninHallDialog);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    database.getReference("Halls").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot showSnapshot : dataSnapshot.getChildren()) {
                                    // Extract cinema name
                                    Integer hallId = showSnapshot.child("id").getValue(Integer.class);
                                    if (deleteId.equals(hallId) ) {
                                        nameInHallDialog.setText(showSnapshot.child("name").getValue(String.class));
                                        seatsPerRowInHallDialog.setText(showSnapshot.child("seatsPerRow").getValue(Integer.class).toString());
                                        seatsPerColumnInHallDialog.setText(showSnapshot.child("seatsPerColumn").getValue(Integer.class).toString());
                                    }
                                }
                            } else {
                                // Handle the case where "Cinemas" node doesn't exist
                                Log.d("Firebase", "Cinemas node does not exist");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("Firebase", "Error reading data:", databaseError.toException());
                        }
                    });
                    confirmHall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatabaseReference hallsRef = database.getReference("Halls");
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("name", nameInHallDialog.getText().toString());
                            updates.put("seatsPerRow", Integer.valueOf(seatsPerRowInHallDialog.getText().toString()));
                            updates.put("seatsPerColumn", Integer.valueOf(seatsPerColumnInHallDialog.getText().toString()));
                            // Đường dẫn đến nút của bộ phim cần cập nhật
                            DatabaseReference hallToUpdateRef = hallsRef.child(deleteId.toString());
                            // Thực hiện cập nhật
                            hallToUpdateRef.updateChildren(updates)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Xử lý thành công
                                            Log.d("HallUpdater", "Hall updated successfully");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Xử lý khi thất bại
                                            Log.e("HallUpdater", "Error updating hall", e);
                                        }
                                    });
                            hallDialog.dismiss();
                            Toast.makeText(ManagerEditAndDeleteActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
                        }
                    });

                    cancelHall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            hallDialog.dismiss();
                            Toast.makeText(ManagerEditAndDeleteActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                    hallDialog.show();
                } else if (deleteType.equals("Shows")) {
                    showDialog.setContentView(R.layout.dialog_show);
                    showDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    showDialog.setCancelable(false);
                    showDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    confirmShow = showDialog.findViewById(R.id.confirmShow);
                    cancelShow = showDialog.findViewById(R.id.cancelShow);
                    startTimeInShowDialog = showDialog.findViewById(R.id.startTimeInShowDialog);
                    endTimeInShowDialog = showDialog.findViewById(R.id.endTimeInShowDialog);
                    dateInShowDialog = showDialog.findViewById(R.id.dateInShowDialog);
                    chooseMovieSpinnerInShowDialog = showDialog.findViewById(R.id.chooseMovieSpinnerInShowDialog);
                    chooseHallSpinnerInShowDialog = showDialog.findViewById(R.id.chooseHallSpinnerInShowDialog);
                    List<String> movieNamesListInShowDialog = new ArrayList<>();
                    List<Integer> movieIdListInShowDialog = new ArrayList<>();
                    List<String> hallNamesListInShowDialog = new ArrayList<>();
                    List<Integer> hallIdListInShowDialog = new ArrayList<>();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    database.getReference("Shows").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot showSnapshot : dataSnapshot.getChildren()) {
                                    Integer showId = showSnapshot.child("id").getValue(Integer.class);
                                    if (deleteId.equals(showId)) {
                                        startTimeInShowDialog.setText(showSnapshot.child("startTime").getValue(String.class));
                                        endTimeInShowDialog.setText(showSnapshot.child("endTime").getValue(String.class));
                                        dateInShowDialog.setText(showSnapshot.child("date").getValue(String.class));
                                        Integer movieIdNow = showSnapshot.child("movieId").getValue(Integer.class);
                                        Integer hallIdNow = showSnapshot.child("hallId").getValue(Integer.class);
                                        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                                        database1.getReference("Movies").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                                                        String movieName = movieSnapshot.child("name").getValue(String.class);
                                                        Integer movieId = movieSnapshot.child("id").getValue(Integer.class);
                                                        if (!(movieIdNow.equals(movieSnapshot.child("id").getValue(Integer.class)))) {
                                                            movieNamesListInShowDialog.add(movieName);
                                                            movieIdListInShowDialog.add(movieId);
                                                        } else {
                                                            movieNamesListInShowDialog.add(0, movieName);
                                                            movieIdListInShowDialog.add(0, movieId);
                                                        }
                                                    }
                                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, movieNamesListInShowDialog);
                                                    chooseMovieSpinnerInShowDialog.setAdapter(adapter);
                                                } else {
                                                    Log.d("Firebase", "Cinemas node does not exist");
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                Log.e("Firebase", "Error reading data:", databaseError.toException());
                                            }
                                        });

                                        chooseMovieSpinnerInShowDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                movieIdInShowDialog = movieIdListInShowDialog.get(position);
                                            }
                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {
                                                // Handle the case where no item is selected
                                                Log.d("Spinner", "No item selected");
                                            }
                                        });
                                        database1.getReference("Halls").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot hallSnapshot : dataSnapshot.getChildren()) {
                                                        String hallName = hallSnapshot.child("name").getValue(String.class);
                                                        Integer hallId = hallSnapshot.child("id").getValue(Integer.class);
                                                        if (!(hallIdNow.equals(hallSnapshot.child("id").getValue(Integer.class)))) {
                                                            hallNamesListInShowDialog.add(hallName);
                                                            hallIdListInShowDialog.add(hallId);
                                                        } else {
                                                            hallNamesListInShowDialog.add(0, hallName);
                                                            hallIdListInShowDialog.add(0, hallId);
                                                        }
                                                    }
                                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, hallNamesListInShowDialog);
                                                    chooseHallSpinnerInShowDialog.setAdapter(adapter);
                                                } else {
                                                    Log.d("Firebase", "Cinemas node does not exist");
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                Log.e("Firebase", "Error reading data:", databaseError.toException());
                                            }
                                        });

                                        chooseHallSpinnerInShowDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                // Get the selected cinema name from the Spinner
                                                hallIdInShowDialog = hallIdListInShowDialog.get(position);

                                            }
                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {
                                                // Handle the case where no item is selected
                                                Log.d("Spinner", "No item selected");
                                            }
                                        });
                                    }
                                }
                            } else {
                                // Handle the case where "Cinemas" node doesn't exist
                                Log.d("Firebase", "Cinemas node does not exist");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("Firebase", "Error reading data:", databaseError.toException());
                        }
                    });
                    confirmShow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatabaseReference showsRef = database.getReference("Shows");
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("startTime", startTimeInShowDialog.getText().toString());
                            updates.put("endTime", endTimeInShowDialog.getText().toString());
                            updates.put("date", dateInShowDialog.getText().toString());
                            updates.put("movieId", (Integer) movieIdInShowDialog);
                            updates.put("hallid", (Integer) hallIdInShowDialog);
                            // Đường dẫn đến nút của bộ phim cần cập nhật
                            DatabaseReference showToUpdateRef = showsRef.child(deleteId.toString());
                            // Thực hiện cập nhật
                            showToUpdateRef.updateChildren(updates)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Xử lý thành công
                                            Log.d("ShowUpdater", "Show updated successfully");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Xử lý khi thất bại
                                            Log.e("ShowUpdater", "Error updating show", e);
                                        }
                                    });
                            showDialog.dismiss();
                            Toast.makeText(ManagerEditAndDeleteActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
                        }
                    });

                    cancelShow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialog.dismiss();
                            Toast.makeText(ManagerEditAndDeleteActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                    showDialog.show();
                }
            }
        });
    }
}