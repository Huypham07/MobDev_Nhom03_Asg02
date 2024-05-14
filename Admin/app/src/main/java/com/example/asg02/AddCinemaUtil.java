package com.example.asg02;

public class AddCinemaUtil {
    boolean validateAddCinemaUtil(String cinemaName, String cinemaEmail, String cinemaPassword) {
        if (cinemaName.isEmpty() || cinemaEmail.isEmpty() || cinemaPassword.isEmpty()) {
            return false;
        }

        if (!cinemaEmail.endsWith("@star3.cineplex.com")) {
            return false;
        }

        if (!cinemaPassword.startsWith("#star3cineplex#")) {
            return false;
        }

        return true;
    }
}
