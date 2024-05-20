package com.example.asg02;

import android.content.SharedPreferences;

import com.example.asg02.controller.CreateHallController;
import com.example.asg02.model.Hall;

public class AddHallLogic {
    private CreateHallController createHallController;
    private SharedPreferences sharedPreferences;

    public AddHallLogic(CreateHallController createHallController, SharedPreferences sharedPreferences) {
        this.createHallController = createHallController;
        this.sharedPreferences = sharedPreferences;
    }

    public void handleAddHall(String hallName, int seatsPerRow, int seatsPerColumn, int cinemaId) {
        if (hallName == null || hallName.isEmpty()) {
            return; // Hall name is required
        }

        if (seatsPerRow <= 0 || seatsPerColumn <= 0) {
            return; // Seats per row and column must be positive
        }

        Hall hall = new Hall(hallName, seatsPerRow, seatsPerColumn, cinemaId);
        createHallController.createHall(hall);
    }
}
