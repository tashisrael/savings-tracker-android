package com.bemoneywiser.telekako;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewExpenseActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    private static DecimalFormat df = new DecimalFormat("###.##");
    Startsaving data1;

    ImageView leftimage , rightimage;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    EditText newstartinamount,newexpenseitem, newexpenseamount, newexpensedeadlinedate,exppercent1,shwsld,shwexpense;
    Button submitexpense;
    String captureselected1,captureselected2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        newstartinamount= findViewById(R.id.itemStartingAmount);
        newexpenseamount= findViewById(R.id.itemamount);
        newexpensedeadlinedate= findViewById(R.id.expenseDeadline);
        exppercent1 = findViewById(R.id.exppercent);
        shwsld = findViewById(R.id.shwslctd);
        shwexpense = findViewById(R.id.shwslctd1);
        data1=new Startsaving(this);

        submitexpense = findViewById(R.id.btnexpenseadd);

        newexpensedeadlinedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(NewExpenseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                newexpensedeadlinedate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });


        Spinner dropdown = findViewById(R.id.currency1);
        //create a list of items for the spinner
        String[] items = new String[]{"","UGX","USD","EURO","GBP","TZSH","KSH"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected1=(String) parent.getItemAtPosition(position);
                shwsld.setText(captureselected1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner dropdown1 = findViewById(R.id.expense);
        //create a list of items for the spinner
        String[] items1 = new String[]{"SELECT THE EXPENSE","Food","Rent","School Fees","Entertainment","Donations","Kids Clothing","Adult Clothing","Water","Electricity","Pay TV","Airtime","Data","Fuel","Car Maintenance","Transport","Home Repairs","Staff Salaries","Furniture","Construction Materials","Shoes","Other.."};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items1);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter1);

        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected2=(String) parent.getItemAtPosition(position);
                shwexpense.setText(captureselected2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewExpenseActivity.this,ExpensesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void subExp(View view) {
       if (TextUtils.isEmpty(newstartinamount.getText())){
            newstartinamount.setError("Please enter your expense total");
        }
        else if (TextUtils.isEmpty(newexpenseamount.getText())){
            newexpenseamount.setError("Please enter your starting amount");
        }
        else if (TextUtils.isEmpty(newexpensedeadlinedate.getText())){
            newexpensedeadlinedate.setError("Please select date");
        }

        else {
            String amount1 = newstartinamount.getText().toString();
            String startingamount1 = newexpenseamount.getText().toString();

            double percentage = (Double.parseDouble(startingamount1)/Double.parseDouble(amount1))*100;
            String s = df.format(percentage);
            exppercent1.setText(s);

            String date3 = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

            if((Integer.parseInt(newexpenseamount.getText().toString()))>(Integer.parseInt(newstartinamount.getText().toString()))){
                newexpenseamount.setError("Amount exceeds the savings goal total");
            }
            else{

                Boolean test1=data1.saveInfo1(shwexpense.getText().toString(),amount1,startingamount1,newexpensedeadlinedate.getText().toString(),exppercent1.getText().toString(),date3,shwsld.getText().toString());
                if (test1==true){
                    //Toast.makeText(this,"No Registered Goals Currently. Please Register a goal!!",Toast.LENGTH_SHORT).show();
                    // Toast.makeText(this,"New expense registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewExpenseActivity.this, ExpensesActivity.class);
                    Toast.makeText(this,"New expense registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,"Registration Unsuccessful", Toast.LENGTH_LONG).show();
                }

                newstartinamount.getText().clear();
                newexpenseamount.getText().clear();
                newexpensedeadlinedate.getText().clear();

            }
        }
    }
}