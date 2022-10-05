package com.bemoneywiser.telekako;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class ExpenseActivity3 extends AppCompatActivity {

    private static DecimalFormat df = new DecimalFormat("###.##");
    ImageView leftimage , rightimage;
    EditText goalamount;
    Button updatebutton;
    String data1,data2,data3,data4;
    String percent;
    TextView showtxt,showper,showamt,showper1,percentage,ramt;

    //for the dialog activity
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    EditText newstartinamount,newexpenseitem, newexpenseamount, newexpensedeadlinedate;
    Button submitexpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense3);

        goalamount=findViewById(R.id.targetitem);
        showtxt=findViewById(R.id.showamount);
        showper=findViewById(R.id.showpercent);
        showamt=findViewById(R.id.showperamt);
        showper1=findViewById(R.id.showper2);
        ramt=findViewById(R.id.realamt);
        percentage=findViewById(R.id.showpercentage);

        String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView daten= findViewById(R.id.date4);
        daten.setText(date);


        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExpenseActivity3.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewExpenseItemDialog();
            }
        });


        updatebutton=findViewById(R.id.btnupdate1);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(goalamount.getText())) {
                    goalamount.setError("Please enter amount");
                } else {
                    Startsaving myDB = new Startsaving(ExpenseActivity3.this);

                    int newamt = (Integer.parseInt(showamt.getText().toString())) + (Integer.parseInt(goalamount.getText().toString()));
                    String newAmt = Integer.toString(newamt);
                    showper1.setText(newAmt);

                    double per2 = ((Double.parseDouble(showper1.getText().toString())) / (Double.parseDouble(ramt.getText().toString()))) * 100;
                    String percenta = df.format(per2);
                    percentage.setText(percenta);

                    if((Integer.parseInt(showper1.getText().toString()))>(Integer.parseInt(ramt.getText().toString()))){
                        goalamount.setError("Amount exceeds the budgeted expense total");
                    }
                    else{

                        myDB.updateExp(showper1.getText().toString(), percentage.getText().toString(), data1);
                        Intent intent = new Intent(ExpenseActivity3.this, HomeDashboard.class);
                        startActivity(intent);
                    }
                }
            }

        });

        getData();
        setData();

    }

    public void createNewExpenseItemDialog(){
        dialogBuilder= new AlertDialog.Builder(this);
        final View expenseItemPopupView= getLayoutInflater().inflate(R.layout.expensepopup,null);
        newstartinamount=expenseItemPopupView.findViewById(R.id.itemStartingAmount);
        newexpenseitem=expenseItemPopupView.findViewById(R.id.itemExpense);
        newexpenseamount= expenseItemPopupView.findViewById(R.id.itemamount);
        newexpensedeadlinedate= expenseItemPopupView.findViewById(R.id.expenseDeadline);

        submitexpense = expenseItemPopupView.findViewById(R.id.btnexpenseadd);
        // cancelexpense= expenseItemPopupView.findViewById(R.id.cancelbtn);

        dialogBuilder.setView(expenseItemPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        submitexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define the submit button here
            }
        });

       /* cancelexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        }); */
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

}