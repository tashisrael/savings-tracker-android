package com.bemoneywiser.telekako;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseActivity2 extends AppCompatActivity {

    ImageView newTransactbtn;
    ImageView leftimage , rightimage;
    TextView title,subtitle,sdate,soverdue,subtitle2,samount,startingdate,initialamount,finalamount,inCurrency,fiCurrency;
    String data1,data2,data3,data4,data5,data6,data7;

    //for the dialog activity
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    EditText newstartinamount,newexpenseitem, newexpenseamount, newexpensedeadlinedate;
    Button submitexpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense2);

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


        newTransactbtn = findViewById(R.id.addbutton);
        newTransactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ExpenseActivity2.this,ExpenseActivity3.class);
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
                Intent intent=new Intent(ExpenseActivity2.this,HomeDashboard.class);
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

    //geting data and setting it
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
    }
}