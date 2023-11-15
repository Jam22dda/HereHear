package com.ssafy.herehear.presentation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.NotificationCompat;

import com.ssafy.herehear.R;

public class NotificationHelper extends ContextWrapper {

    private NotificationManager notiManager;

    public NotificationHelper(Context base) {
        super(base);

        createChannels();
    }

    public void createChannels() {
        NotificationChannel channel1 = new NotificationChannel(
                "channel1",
                "Channel 1",
                NotificationManager.IMPORTANCE_DEFAULT
        );

        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLockscreenVisibility(android.app.Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
    }

    public NotificationManager getManager() {
        if (notiManager == null) {
            notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public NotificationCompat.Builder getChannel1Notification() {
        return new NotificationCompat.Builder(getApplicationContext(), "channel1")
                .setContentTitle("알림")
                .setContentText("알림매니저 실험중")
                .setSmallIcon(R.drawable.ditto);
    }
}
