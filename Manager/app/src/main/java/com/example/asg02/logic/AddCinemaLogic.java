package com.example.asg02.logic;

import android.content.SharedPreferences;

import com.example.asg02.controller.CreateCinemaController;
import com.example.asg02.model.Cinema;

public class AddCinemaLogic {
    private CreateCinemaController createCinemaController;
    private SharedPreferences preferences;

    public AddCinemaLogic(CreateCinemaController createCinemaController, SharedPreferences preferences) {
        this.createCinemaController = createCinemaController;
        this.preferences = preferences;
    }

    public void handleAddCinema(String name, double latitude, double longitude, String detailAddress) {
        String email = preferences.getString("email", "error");
        if (email == null) {
            email = "error";
        }
        Cinema cinema = new Cinema(name, latitude, longitude, detailAddress, email);
        createCinemaController.createCinema(cinema);
    }
}