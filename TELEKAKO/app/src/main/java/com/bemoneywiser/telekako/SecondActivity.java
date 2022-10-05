package com.bemoneywiser.telekako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    ImageView newTransactbtn;
    ImageView leftimage , rightimage;
    TextView title,subtitle,sdate,soverdue,subtitle2,samount,startingdate,initialamount,finalamount,inCurrency,fiCurrency,remaincash;
    String data1,data2,data3,data4,data5,data6,data7;
    ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        title=findViewById(R.id.stitle);
        subtitle=findViewById(R.id.sSubtitle);
        sdate=findViewById(R.id.sDeadline);
        soverdue=findViewById(R.id.sover);
        subtitle2=findViewById(R.id.sSubtitle3);
        samount=findViewById(R.id.startMoney);
        startingdate=findViewById(R.id.startingdate);
        initialamount=findViewById(R.id.initialAmount);
        finalamount=findViewById(R.id.finalAmount);
        inCurrency=findViewById(R.id.initialCurrency);
        fiCurrency=findViewById(R.id.finalcurrency);
        remaincash = findViewById(R.id.remainMoney);


        //progressBar1.setProgress(Integer.parseInt(subtitle.getText().toString()));

        newTransactbtn = findViewById(R.id.addbutton);
        newTransactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SecondActivity.this,AddNewTransaction.class);
                intent.putExtra("data1",title.getText());
                intent.putExtra("data2",subtitle.getText());
                intent.putExtra("data3",samount.getText());
                intent.putExtra("data4",soverdue.getText());
                startActivity(intent);
            }
        });

        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,MySavingsFile.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        getData();
        setData();

    }

    private void getData(){
      if(getIntent().hasExtra("data1") && getIntent().hasExtra("data2")
                && getIntent().hasExtra("data3") && getIntent().hasExtra("data4") && getIntent().hasExtra("data5")){

          data1=getIntent().getStringExtra("data1");
          data2=getIntent().getStringExtra("data2");
          data3=getIntent().getStringExtra("data3");
          data4=getIntent().getStringExtra("data4");
          data5=getIntent().getStringExtra("data5");
          data6=getIntent().getStringExtra("data6");
          data7=getIntent().getStringExtra("data7");
          /*String resultsw = getIntent().getStringExtra("data2");
          ProgressBar bar = findViewById(R.id.progress2);
          bar.setProgress(Integer.parseInt(resultsw));*/

        }else {
          Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
      }
    }
    private void setData(){
       title.setText(data1);
       subtitle.setText(data2);
       sdate.setText(data3);
       soverdue.setText(data4);
       samount.setText(data5);
       startingdate.setText(data6);
       initialamount.setText(data5);
       finalamount.setText(data4);
       inCurrency.setText(data7);
       fiCurrency.setText(data7);

        int newamt = (Integer.parseInt(soverdue.getText().toString())) - (Integer.parseInt(samount.getText().toString()));
        String newAmt = Integer.toString(newamt);
        remaincash.setText(newAmt);
    }

}