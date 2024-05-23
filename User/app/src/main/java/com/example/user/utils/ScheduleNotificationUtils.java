package com.example.user.utils;

import android.content.Context;

import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.user.service.NotificationWorker;

import java.util.concurrent.TimeUnit;

public class ScheduleNotificationUtils {
    public static void scheduleNotificationWork(Context context, String userId) {
        Data inputData = new Data.Builder()
                .putString("userId", userId)
                .build();


        // setup a periodic work request to check for expired bookings
        PeriodicWorkRequest notificationWorkRequest
                = new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES)
                .setInputData(inputData).build();
//        WorkManager.getInstance(context).enqueue(notificationWorkRequest);
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "expiry_check",
                ExistingPeriodicWorkPolicy.REPLACE,
                notificationWorkRequest
        );
    }
}
