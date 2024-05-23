package com.example.user.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.user.R;
import com.example.user.controller.booking.GetBookingController;
import com.example.user.model.Booking;
import com.example.user.model.Notification;
import com.example.user.utils.DateTimeUtils;
import com.example.user.utils.FirebaseUtils;
import com.example.user.view.MainActivity;
import com.google.firebase.database.DatabaseReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String userId = getInputData().getString("userId");

        if (userId == null) {
            return Result.failure();
        }

        GetBookingController controller = new GetBookingController();
        controller.getBookings(userId
            , (booking) -> {
                if (booking.getStatus() == Booking.STATUS_AVAILABLE) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
                    try {
                        Date expirationDate = sdf.parse(booking.getShow().getDate() + " - " + booking.getShow().getStartTime());
                        long expirationTimeMillis = expirationDate.getTime();

                        if (expirationTimeMillis - System.currentTimeMillis() <= 3 * 60 * 60 * 1000) {
                            String title = "Thông báo vé sắp hết hạn";
                            String message = "Vé xem phim mã " + booking.getId() + " của bạn sắp hết hạn, Đừng quên sử dụng nhé!";
                            Log.e("a", message);
                            sendNotification(title, message, userId);
                        }

                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );

        return Result.success();
    }

    private void sendNotification(String title, String message, String userId) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification();

        String channel_id = "ticket_expiry_channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channel_id, "ticket expiry", importance);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(soundUri, audioAttributes);

            notificationManager.createNotificationChannel(channel);
        }
        // Tạo NotificationCompat.Builder để tạo thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notification)
                .setSound(soundUri);

        notification.setUserId(userId);
        notification.setContent(message);
        notification.setTimestamp(System.currentTimeMillis());
        notification.setStatus(Notification.STATUS_UNREAD);
        notification.setId(notification.hashCode());
        //save into firebase
        FirebaseUtils.getDatabaseReference("Notifications").child(userId).child(channel_id)
                .child(String.valueOf(notification.getId())).setValue(notification);

        // Hiển thị thông báo
        notificationManager.notify(notification.getId(), builder.build());
    }
}