package com.bemoneywiser.telekako;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OthersActivity extends AppCompatActivity {

    private static DecimalFormat df = new DecimalFormat("###.##");
    ImageView leftimage , rightimage;
    Startsaving data;
    DatePickerDialog datePickerDialog;
    EditText goalx,amountx,startingamountx,datex,percentx,showstx,shwgoalx;
    Button btnx;
    TextView emailx;
    String captureselectedx,captureselected1x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        Spinner dropdown = findViewById(R.id.currency);
        //create a list of items for the spinner
        String[] items = new String[]{"","UGX","USD","EURO","GBP","TZSH","KSH"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselectedx=(String) parent.getItemAtPosition(position);
                showstx.setText(captureselectedx);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner dropdown2 = findViewById(R.id.reminder);
        String[] items2 = new String[]{"","DAILY","WEEKLY","MONTHLY"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items2);
        //set the spinners adapter to the previously created one.
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected1x=(String) parent.getItemAtPosition(position);
                if(parent.getItemAtPosition(position).equals("DAILY")){

                    Intent intent = new Intent(OthersActivity.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(OthersActivity.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();

                    long tenSeconds = 1000 * 86400;

                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            timeAtButtonClick + tenSeconds, 1000 * 86400,
                            pendingIntent);
                }
                else if(parent.getItemAtPosition(position).equals("WEEKLY")){

                    Intent intent = new Intent(OthersActivity.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(OthersActivity.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();

                    long tensSeconds = 1000 * 604800;

                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            timeAtButtonClick + tensSeconds, 1000 * 604800,
                            pendingIntent);
                }
                else if(parent.getItemAtPosition(position).equals("MONTHLY")){

                    Intent intent = new Intent(OthersActivity.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(OthersActivity.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();

                    long tenxSeconds = 1000 * 2592000;

                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            timeAtButtonClick + tenxSeconds, 1000 * 2592000,
                            pendingIntent);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        goalx = findViewById(R.id.goalx);
        showstx = findViewById(R.id.showselectedx);
        percentx = findViewById(R.id.percentx);
        amountx = findViewById(R.id.targetamountx);
        startingamountx = findViewById(R.id.startingamountx);
        data=new Startsaving(this);
        datex = findViewById(R.id.datex);
        shwgoalx = findViewById(R.id.showemail111x);


        datex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(OthersActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datex.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });


        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OthersActivity.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OthersActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    public void submit(View view) {

        if (TextUtils.isEmpty(goalx.getText())){
            goalx.setError("Please enter your savings goal");
        }
        else if (TextUtils.isEmpty(amountx.getText())){
            amountx.setError("Please enter your savings total");
        }
        else if (TextUtils.isEmpty(startingamountx.getText())){
            startingamountx.setError("Please enter your starting amount");
        }
        else if (TextUtils.isEmpty(datex.getText())){
            datex.setError("Please select date");
        }

        else {
            String goal11 = goalx.getText().toString();
            String amount1 = amountx.getText().toString();
            String startingamount1 = startingamountx.getText().toString();

            double percentage = (Double.parseDouble(startingamount1)/Double.parseDouble(amount1))*100;
            String s = df.format(percentage);
            percentx.setText(s);

            String date2 = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

            if((Integer.parseInt(startingamountx.getText().toString()))>(Integer.parseInt(amountx.getText().toString()))){
                startingamountx.setError("Amount exceeds the savings goal total");
            }
            else{

                Boolean test=data.saveInfo(goal11,amount1,startingamount1,datex.getText().toString(),percentx.getText().toString(),date2,showstx.getText().toString());
                if (test==true){


                    Toast.makeText(this,"Goal registered successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(OthersActivity.this, MySavingsFile.class);


                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,"Registration Unsuccessful", Toast.LENGTH_LONG).show();
                }

                goalx.getText().clear();
                amountx.getText().clear();
                startingamountx.getText().clear();
                datex.getText().clear();

            }
        }




    }
}