package com.bemoneywiser.telekako;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        TextView textView = findViewById(R.id.notetext);
        String message = getIntent().getStringExtra("message");
        textView.setText(message);
    }
}