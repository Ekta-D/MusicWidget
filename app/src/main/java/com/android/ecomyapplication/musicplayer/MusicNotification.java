//package com.android.ecomyapplication.musicplayer;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.content.Context;
//import android.os.Build;
//
//import com.android.ecomyapplication.R;
//
//import androidx.core.app.NotificationCompat;
//
//public class MusicNotification {
//
//
//    NotificationManager notificationManager;
//     final int notificationID;
//
//    public MusicNotification(Context context, int notificationiD, String title, String artist) {
//        this.notificationID=notificationiD;
//        notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel("music_channel01",title,importance);
//            channel.setDescription(artist);
//            channel.enableVibration(true);
//            channel.enableLights(true);
//            channel.setLightColor(R.color.colorPrimary);
//
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"music_channel01")
//                .setContentTitle(title)
//                .setContentText(artist)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setAutoCancel(true);
//        notificationManager.notify(1, builder.build());
//
//    }
//
//    public void update(String title, String artist, boolean isPlaying, boolean isShuffleOn){
//        builder.setContentTitle(title)
//                .setContentText(artist)
//                .setWhen(System.currentTimeMillis());
//
//        if (isPlaying)
//            playPauseAction.icon = R.drawable.ic_pause_white_36dp;
//        else
//            playPauseAction.icon = R.drawable.ic_play_arrow_white_36dp;
//
//        if (isShuffleOn)
//            shuffleAction.icon = R.drawable.shuffle_on;
//        else
//            shuffleAction.icon = R.drawable.shuffle_off;
//
//        notification = builder.build();
//        manager.notify(notificationID, notification);
//    }
//
//}
