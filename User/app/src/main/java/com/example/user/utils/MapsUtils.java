package com.example.user.utils;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class MapsUtils {
    public static float calculateDistance(LatLng currentLatLng, LatLng cinemaLatLng) {
        float[] results = new float[1];
        android.location.Location.distanceBetween(currentLatLng.latitude, currentLatLng.longitude, cinemaLatLng.latitude, cinemaLatLng.longitude, results);
        float distanceInMeters = results[0];
        float distanceInKm = distanceInMeters / 1000;
        return distanceInKm;
    }
}
