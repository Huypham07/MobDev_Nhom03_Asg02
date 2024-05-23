package com.example.asg02.logic;

import android.content.SharedPreferences;

import com.example.asg02.controller.CreateShowController;
import com.example.asg02.model.Show;

public class AddShowLogic {
    private CreateShowController createShowController;
    private SharedPreferences sharedPreferences;

    public AddShowLogic(CreateShowController createShowController, SharedPreferences sharedPreferences) {
        this.createShowController = createShowController;
        this.sharedPreferences = sharedPreferences;
    }

    public void handleAddShow(int cinemaId, int hallId, int movieId, String startTime, String endTime, String date) {
        if (date == null || startTime.isEmpty() || endTime.isEmpty() || cinemaId <= 0) {
            return; // Invalid input, do nothing
        }

        Show show = new Show(cinemaId, hallId, movieId, startTime, endTime, date);
        createShowController.createShow(show);
    }
}
