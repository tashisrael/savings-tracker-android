package com.bemoneywiser.telekako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeDashboard extends AppCompatActivity {
      CardView cardOne,cardTwo,cardthree,cardfour,cardfive,cardsix;
      ImageView leftimage , rightimage;
    ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);

        cardOne = findViewById(R.id.card1);
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_contact:
                        Intent Email = new Intent(Intent.ACTION_SEND);
                        Email.setType("text/plain");
                        Email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "bemoneywiser@gmail.com" });
                        Email.putExtra(Intent.EXTRA_SUBJECT, "I would like to inquire about Telekako! Save Some Mo!");
                        Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
                        startActivity(Intent.createChooser(Email, "Contact Us Directly:"));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_help:
                        startActivity(new Intent(getApplicationContext(),
                                AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,Saving.class);
                startActivity(intent);
               // finish();
            }
        });
        cardTwo = findViewById(R.id.card2);
        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,MySavingsFile.class);
                startActivity(intent);
                // finish();
            }
        });
        cardfive= findViewById(R.id.card5);
        cardfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,ProfileActivity.class);
                startActivity(intent);
                // finish();
            }
        });
        cardfour= findViewById(R.id.card4);
        cardfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,AdvisorsActivity.class);
                startActivity(intent);
                // finish();
            }
        });
        cardthree= findViewById(R.id.card3);
        cardthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,HelpActivity.class);
                startActivity(intent);
            }
        });

        cardsix= findViewById(R.id.card6);
        cardsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeDashboard.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        onBackPressed();

    }
    public void onBackPressed() {
        try {
            progressdialog.dismiss();
        }
        catch (Exception e) {
            e.getMessage();
        }
    }
}