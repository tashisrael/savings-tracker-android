package com.bemoneywiser.telekako;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        Intent goToIntent= new Intent(context,Saving.class);

        TaskStackBuilder stackBuilder=TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Saving.class);
        stackBuilder.addNextIntent(goToIntent);

        PendingIntent pendingIntent= stackBuilder.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context);

        Notification notification= builder.setContentTitle("Telekako")
                .setContentText("Please check your saving goals")
                .setTicker("New Message Alert!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.t_icon)
                .setContentIntent(pendingIntent).build();
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
