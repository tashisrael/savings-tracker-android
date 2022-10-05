package com.bemoneywiser.telekako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
        Button btngetstd, signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // addNotification();


        //AlarmService
        /*
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);

        Intent intent= new Intent("singh.ajit.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast= PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),broadcast);
        }  */


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btngetstd= findViewById(R.id.btngetstarted);
        btngetstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            /*    String message = "You have made your payment for street parking ticket fees thanks.";
                NotificationCompat.Builder builder=  new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle("Telekako Savings")
                        .setContentText(message)
                        .setAutoCancel(true);
                Intent intent = new Intent(MainActivity.this,NotificationsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);
                PendingIntent pendingIntent= PendingIntent.getActivity(MainActivity.this,
                        0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());  */
                //finish();
            }
        });

    }

  /*  private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.t_icon)
                        .setContentTitle("Telekako")
                        .setContentText("Please check your goals");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    } */
}