package com.bemoneywiser.telekako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class FlashActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(FlashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}