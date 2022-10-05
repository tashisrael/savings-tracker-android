package com.bemoneywiser.telekako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Saving extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static DecimalFormat df = new DecimalFormat("###.##");
    ImageView leftimage , rightimage;
    Startsaving data;
    DatePickerDialog datePickerDialog;
    EditText amount,startingamount,date,percent,showst,shwgoal;
    Button btn;
    TextView email;
    String captureselected,captureselected1,captureselected2;

    private FirebaseUser user1;
    private DatabaseReference reference;
    private String userID;
    private int importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);
        createNotificationChannel();
        mAuth = FirebaseAuth.getInstance();
          //get spinner from the xml

        Spinner dropdown = findViewById(R.id.currency);
        //create a list of items for the spinner
        String[] items = new String[]{"Choose...","UGX","USD","EURO","GBP","TZSH","KSH"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        /*Intent notifyIntent = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(
                context, NOTIFICATION_REMINDER_NIGHT, notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT
        );
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60*60*24, pendingIntent); */

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected=(String) parent.getItemAtPosition(position);
                showst.setText(captureselected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner dropdown2 = findViewById(R.id.reminder);
        String[] items2 = new String[]{"Choose...","DAILY","WEEKLY","MONTHLY"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items2);
        //set the spinners adapter to the previously created one.
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected2=(String) parent.getItemAtPosition(position);
                if(parent.getItemAtPosition(position).equals("DAILY")){

                    Intent intent = new Intent(Saving.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(Saving.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();

                    long tenSeconds = 1000 * 86400;

                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            timeAtButtonClick + tenSeconds, 1000 * 86400,
                            pendingIntent);
                }
                else if(parent.getItemAtPosition(position).equals("WEEKLY")){

                    Intent intent = new Intent(Saving.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(Saving.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();

                    long tensSeconds = 1000 * 604800;

                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            timeAtButtonClick + tensSeconds, 1000 * 604800,
                            pendingIntent);
                }
                else if(parent.getItemAtPosition(position).equals("MONTHLY")){

                    Intent intent = new Intent(Saving.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(Saving.this, 0, intent, 0);

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


        Spinner dropdown1 = findViewById(R.id.goal);
        //create a list of items for the spinner
        String[] items1 = new String[]{"SELECT THE GOAL","School Fees","Land","Car","Home Appliance","Holiday/Vacation","Retirement","Emergency","Home Construction & Repairs","Furniture","Emergencies","Medical","Phone","Baby","Other.."};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items1);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter1);

        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected1=(String) parent.getItemAtPosition(position);
                shwgoal.setText(captureselected1);
                if(parent.getItemAtPosition(position).equals("Other..")){
                    Intent intent=new Intent(Saving.this,OthersActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        showst = findViewById(R.id.showselected);
        percent = findViewById(R.id.percent);
        amount = findViewById(R.id.targetamount);
        startingamount = findViewById(R.id.startingamount);
        data=new Startsaving(this);
        date = findViewById(R.id.date);
        shwgoal = findViewById(R.id.showemail111);



       /* user1=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID = user1.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String Email = userProfile.Email;

                    email.setText(Email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Saving.this,"Connect to internet! Something went wrong",Toast.LENGTH_LONG).show();

            }
        });*/





        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Saving.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });
        //create a list of items for the spinner
       // String[] items = new String[]{"Per Day","Per week","Per Month"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //set the spinners adapter to the previously created one.
     //   dropdown.setAdapter(adapter);

        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Saving.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Saving.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

       /* User1Crud use = new User1Crud();

        btn = findViewById(R.id.btnsubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(goal.getText())){
                    goal.setError("Please enter the goal");
                }
                else if (TextUtils.isEmpty(amount.getText())){
                    amount.setError("Please enter your savings total");
                }
                else if (TextUtils.isEmpty(startingamount.getText())){
                    startingamount.setError("Please enter your starting amount");
                }
                else if (TextUtils.isEmpty(date.getText())){
                    date.setError("Please select date");
                }

                else {
                    String Goal = goal.getText().toString().trim();
                    String Amount = amount.getText().toString().trim();
                    String StartingAmt = startingamount.getText().toString().trim();
                    String Date = date.getText().toString().trim();
                    String Currency = showst.getText().toString().trim();
                    String Email1 = email.getText().toString().trim();

                    String amount1 = amount.getText().toString();
                    String startingamount1 = startingamount.getText().toString();
                    double percentage = (Double.parseDouble(startingamount1)/Double.parseDouble(amount1))*100;
                    String s = df.format(percentage);
                    percent.setText(s);
                    String Percentage = percent.getText().toString().trim();

                    User1 users1 = new User1(Email1, Goal, Amount, StartingAmt, Date, Currency, Percentage);
                    use.add(users1).addOnSuccessListener(suc->
                    {
                        Intent intent=new Intent(Saving.this,MySavingsFile.class);
                        Toast.makeText(Saving.this, "Successful", Toast.LENGTH_LONG).show();
                        intent.putExtra("emaill",Email1);
                        startActivity(intent);
                    }).addOnFailureListener(er->
                    {
                        Toast.makeText(Saving.this, "UnSuccessful", Toast.LENGTH_SHORT).show();
                    });
                }

            }
        });*/


    }

    public void submit(View view) {

        if (TextUtils.isEmpty(amount.getText())){
            amount.setError("Please enter your savings total");
        }
        else if (TextUtils.isEmpty(startingamount.getText())){
            startingamount.setError("Please enter your starting amount");
        }
        else if (TextUtils.isEmpty(date.getText())){
            date.setError("Please select date");
        }

        else if(shwgoal.getText().toString()== "Other.."){
            Intent intent=new Intent(Saving.this,OthersActivity.class);
            startActivity(intent);
        }
        else{
            //String goal11 = goal.getText().toString();
            String amount1 = amount.getText().toString();
            String startingamount1 = startingamount.getText().toString();

            double percentage = (Double.parseDouble(startingamount1)/Double.parseDouble(amount1))*100;
            String s = df.format(percentage);
            percent.setText(s);

            String date2 = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

            if((Integer.parseInt(startingamount.getText().toString()))>(Integer.parseInt(amount.getText().toString()))){
                startingamount.setError("Amount exceeds the savings goal total");
            }
            else{

                Boolean test=data.saveInfo(shwgoal.getText().toString(),amount1,startingamount1,date.getText().toString(),percent.getText().toString(),date2,showst.getText().toString());
                if (test==true){


                    Toast.makeText(this,"Goal registered successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Saving.this, MySavingsFile.class);

                   /* Toast.makeText(this,"Reminder set!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(Saving.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(Saving.this, 0, intent1, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeButtonClick = System.currentTimeMillis();

                    long tenSecondsInMillis = 1000 * 10;

                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            timeButtonClick + tenSecondsInMillis,
                            pendingIntent);*/

                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,"Registration Unsuccessful", Toast.LENGTH_LONG).show();
                }

                // goal.getText().clear();
                amount.getText().clear();
                startingamount.getText().clear();
                date.getText().clear();

            }
        }

      /*  String message = "You have made your payment for street parking ticket fees thanks.";
        NotificationCompat.Builder builder=  new NotificationCompat.Builder(Saving.this)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("Telekako Savings")
                .setContentText(message)
                .setAutoCancel(true);
        Intent intent = new Intent(Saving.this,NotificationsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message",message);
        PendingIntent pendingIntent= PendingIntent.getActivity(Saving.this,
                0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }*/


    }

    private void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "LenubitReminderChannel";
            String description = "Channel for Lenubit Reminder";

            NotificationChannel channel = new NotificationChannel("notifyLenubit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }


}