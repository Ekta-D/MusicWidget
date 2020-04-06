package com.android.ecomyapplication.view;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.ecomyapplication.R;
import com.android.ecomyapplication.service.MusicService;

public class WidgetProvider extends AppWidgetProvider {


    private static final String TAG = "Music Widget";
    public static final String ACTION_PLAY_PAUSE = "com.smartpocket.musicwidget.play_pause";
    public static final String ACTION_STOP = "com.smartpocket.musicwidget.stop";
    public static final String ACTION_NEXT = "com.smartpocket.musicwidget.next";
    public static final String ACTION_PREVIOUS = "com.smartpocket.musicwidget.previous";
    public static final String ACTION_SHUFFLE = "com.smartpocket.musicwidget.shuffle";
    public static final String ACTION_JUMP_TO = "com.smartpocket.musicwidget.jump_to";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
//        associateIntents(context);
//        Log.d(TAG, "Widget's onUpdate()");

        for( int appWidgetId: appWidgetIds)
        {
            Intent intent=new Intent(context, MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context, 0,intent,0);
            RemoteViews remoteViews=new RemoteViews(context.getPackageName() , R.layout.activity_main);
            remoteViews.setOnClickPendingIntent(R.id.butto1, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
        }
    }



    public static RemoteViews getRemoteViews(Context context){
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);






        // For Play/Pause button
//        PendingIntent pendingIntentStart = getPendingIntent(context, WidgetProvider.ACTION_PLAY_PAUSE);
//        remoteViews.setOnClickPendingIntent(R.id.butto1, pendingIntentStart);
//
//        // For Stop button
//        PendingIntent pendingIntentStop = getPendingIntent(context, WidgetProvider.ACTION_STOP);
//        remoteViews.setOnClickPendingIntent(R.id.button7,pendingIntentStop);
//
//        // For Song List activity
//        Intent intent= new Intent(context, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendIntentSongList = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        //remoteViews.setOnClickPendingIntent(R.id.layout_header, pendIntentSongList);

        return remoteViews;
    }


    private void associateIntents(Context context) {

        try {
            RemoteViews remoteViews = getRemoteViews(context);

            // Push update for this widget to the home screen
            ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(thisWidget, remoteViews);
        }
        catch (Exception e)
        {e.printStackTrace();}
    }

    public static PendingIntent getPendingIntent(Context context, String action) {
        Intent intent = new Intent(context, WidgetProvider.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        super.onDeleted(context, appWidgetIds);
        Intent oService = new Intent(context, MusicService.class);
        context.stopService(oService);
        Log.d(TAG, "Deleting widget");
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        final String action = intent.getAction();
        Log.d(TAG, "Widget received action: " + action);

        if ((action.equals(ACTION_PLAY_PAUSE)
                || action.equals(ACTION_NEXT)
                || action.equals(ACTION_STOP)
                || action.equals(ACTION_PREVIOUS)
                || action.equals(ACTION_SHUFFLE)))
        {
            Intent serviceIntent = new Intent(context, MusicService.class);
            serviceIntent.setAction(action);
            context.startService(serviceIntent);
        }
        else
        {
            super.onReceive(context, intent);
        }
    }
}


