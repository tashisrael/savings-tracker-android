package com.bemoneywiser.telekako;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewTransaction extends AppCompatActivity {
    private static DecimalFormat df = new DecimalFormat("###.##");
    Dialog mDialog,mmDialog;
    ImageView leftimage , rightimage;
    EditText goalamount;
    Button updatebutton;
    String data1,data2,data3,data4;
    String percent;
    TextView showtxt,showper,showamt,showper1,percentage,ramt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);
        goalamount=findViewById(R.id.targetitem);
        showtxt=findViewById(R.id.showamount);
        showper=findViewById(R.id.showpercent);
        showamt=findViewById(R.id.showperamt);
        showper1=findViewById(R.id.showper2);
        ramt=findViewById(R.id.realamt);
        percentage=findViewById(R.id.showpercentage);

        mDialog = new Dialog(this);
        mmDialog = new Dialog(this);


        String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView daten= findViewById(R.id.date4);
        daten.setText(date);


        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddNewTransaction.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddNewTransaction.this,ProfileActivity.class);
                startActivity(intent);
            }
        });


        updatebutton=findViewById(R.id.btnupdate);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(goalamount.getText())) {
                    goalamount.setError("Please enter amount");
                } else {
                    Startsaving myDB = new Startsaving(AddNewTransaction.this);

                    int newamt = (Integer.parseInt(showamt.getText().toString())) + (Integer.parseInt(goalamount.getText().toString()));
                    String newAmt = Integer.toString(newamt);
                    showper1.setText(newAmt);

                    double per2 = ((Double.parseDouble(showper1.getText().toString())) / (Double.parseDouble(ramt.getText().toString()))) * 100;
                    String percenta = df.format(per2);
                    percentage.setText(percenta);

                    if((Integer.parseInt(showper1.getText().toString()))>(Integer.parseInt(ramt.getText().toString()))){
                        goalamount.setError("Amount exceeds the savings goal total");
                     }

                     else{
                    myDB.updateInfo(showper1.getText().toString(), percentage.getText().toString(), data1);

                         if (Integer.parseInt(percentage.getText().toString()) == 50){

                             Button ok;
                             mDialog.setContentView(R.layout.popup);
                             mDialog.setCanceledOnTouchOutside(false);
                             ok=mDialog.findViewById(R.id.ok);

                             ok.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent intent=new Intent(AddNewTransaction.this,HomeDashboard.class);
                                     startActivity(intent);

                                 }
                             });

                             mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                             mDialog.show();

                         }

                         else if (Integer.parseInt(percentage.getText().toString()) == 100){

                             Button ok2;
                             mmDialog.setContentView(R.layout.popup2);
                             mmDialog.setCanceledOnTouchOutside(false);
                             ok2=mmDialog.findViewById(R.id.ok2);

                             ok2.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent intent=new Intent(AddNewTransaction.this,HomeDashboard.class);
                                     startActivity(intent);

                                 }
                             });

                             mmDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                             mmDialog.show();

                         }

                         else {
                             Intent intent=new Intent(AddNewTransaction.this,HomeDashboard.class);
                             startActivity(intent);
                         }

                     }
                }
            }

        });

        getData();
        setData();
    }
    private void getData(){
        if(getIntent().hasExtra("data2") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3")  && getIntent().hasExtra("data4")){

            data1=getIntent().getStringExtra("data1");
            data2=getIntent().getStringExtra("data2");
            data3=getIntent().getStringExtra("data3");
            data4=getIntent().getStringExtra("data4");


        }
        else {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        showtxt.setText(data1);
        showper.setText(data2);
        showamt.setText(data3);
        ramt.setText(data4);
    }

   /* private void setData(){
        Startsaving myDB = new Startsaving(AddNewTransaction.this);
        SQLiteDatabase db = myDB.getReadableDatabase();

        Cursor cursor=db.rawQuery("Select * from info",null);
        cursor.moveToFirst();
        String amt = cursor.getString(2);
        String per = cursor.getString(4);
        String realamt = cursor.getString(1);

        showtxt.setText(amt);
        showamt.setText(realamt);
        showper1.setText(per);
    }*/


}