/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.notificationchannels;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

/**
 * Helper class to manage notification channels, and create notifications.
 */
class NotificationHelper extends ContextWrapper {
    // Notification Channel ID for Followers Notifications
    public static final String FOLLOWERS_CHANNEL = "follower";
    // Notification Channel ID for Direct Messages Notifications
    public static final String DIRECT_MESSAGE_CHANNEL = "direct_message";
    private NotificationManager mNotificationManager;

    /**
     * Registers notification channels, which can be used later by individual notifications.
     *
     * @param context The application context
     */
    public NotificationHelper(Context context) {
        super(context);

        // Registering Notification channels for devices running on
        // Android version which is at least Oreo
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            addFollowersChannel();
            addDirectMessagesChannel();
        }

    }

    /**
     * Registers a Notification Channel for "Followers" Notifications
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void addFollowersChannel() {
        // Create the channel object with the unique ID FOLLOWERS_CHANNEL
        NotificationChannel followersChannel =
                new NotificationChannel(
                        FOLLOWERS_CHANNEL,
                        getString(R.string.notification_channel_followers),
                        NotificationManager.IMPORTANCE_DEFAULT);

        // Configure the channel's initial settings
        followersChannel.setLightColor(Color.GREEN);
        followersChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        followersChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 500, 200, 500});
        followersChannel.setShowBadge(false);

        // Submit the notification channel object to the notification manager
        getNotificationManager().createNotificationChannel(followersChannel);
    }

    /**
     * Registers a Notification Channel for "Direct Messages" Notifications
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void addDirectMessagesChannel() {
        // Create the channel object with the unique ID DIRECT_MESSAGE_CHANNEL
        NotificationChannel dmChannel =
                new NotificationChannel(
                        DIRECT_MESSAGE_CHANNEL,
                        getString(R.string.notification_channel_direct_message),
                        NotificationManager.IMPORTANCE_HIGH);

        // Configure the channel's initial settings
        dmChannel.setLightColor(Color.BLUE);
        dmChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        dmChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 500, 200, 500});
        dmChannel.setShowBadge(true);

        // Submit the notification channel object to the notification manager
        getNotificationManager().createNotificationChannel(dmChannel);
    }

    /**
     * Get a follow/un-follow notification
     *
     * <p>Provide the builder rather than the notification itself, since it is useful for making
     * notification changes.
     *
     * @param title the title of the notification
     * @param body  the body text for the notification
     * @return A Notification.Builder configured with the selected channel and details
     */
    @SuppressLint("ObsoleteSdkInt")
    public NotificationCompat.Builder getNotificationFollower(String title, String body) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), FOLLOWERS_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Sets the Notification Priority which is the equivalent of Notification Importance
            // for devices running on Android version less than Oreo
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }

        return builder;
    }

    /**
     * Get a direct message notification
     *
     * <p>Provide the builder rather than the notification itself, since it is useful for making
     * notification changes.
     *
     * @param title Title for notification.
     * @param body  Message for notification.
     * @return A Notification.Builder configured with the selected channel and details
     */
    @SuppressLint("ObsoleteSdkInt")
    public NotificationCompat.Builder getNotificationDM(String title, String body) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), DIRECT_MESSAGE_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Sets the Notification Priority which is the equivalent of Notification Importance
            // for devices running on Android version less than Oreo
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        return builder;
    }

    /**
     * Create a PendingIntent for opening up the MainActivity when the notification is pressed
     *
     * @return A PendingIntent that opens the MainActivity
     */
    private PendingIntent getPendingIntent() {
        Intent openMainIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(openMainIntent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     * Send a notification.
     *
     * @param id           The ID of the notification
     * @param notification The notification object
     */
    public void notify(int id, NotificationCompat.Builder notification) {
        getNotificationManager().notify(id, notification.build());
    }

    /**
     * Get the small icon for this app
     *
     * @return The small icon resource id
     */
    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }

    /**
     * Get the notification mNotificationManager.
     *
     * <p>Utility method as this helper works with it a lot.
     *
     * @return The system service NotificationManager
     */
    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null) {
            mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    /**
     * Get a random name string from resources to add personalization to the notification
     *
     * @return A random name
     */
    public String getRandomName() {
        String[] names = getApplicationContext().getResources().getStringArray(R.array.names_array);
        return names[new Random().nextInt(names.length)];
    }
}
