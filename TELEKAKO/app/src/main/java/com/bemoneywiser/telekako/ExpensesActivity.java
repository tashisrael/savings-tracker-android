package com.bemoneywiser.telekako;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.widget.Toast;

public class ExpensesActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<MyexpensesModelClass> expensesList;

    DatePickerDialog datePickerDialog;
    private static DecimalFormat df = new DecimalFormat("###.##");
    Startsaving data1;

    ImageView leftimage , rightimage;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    EditText newstartinamount,newexpenseitem, newexpenseamount, newexpensedeadlinedate,exppercent1,shwsld;
    Button submitexpense; //cancelexpense;
    String captureselected1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        expensesList = new ArrayList<>();
        mRecyclerView= findViewById(R.id.recyclermyexpenses);
        retrieveExpenses();

       /* Spinner dropdown = findViewById(R.id.currency1);
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
        });*/


        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExpensesActivity.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExpensesActivity.this,NewExpenseActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createNewExpenseItemDialog(){
        dialogBuilder= new AlertDialog.Builder(this);
        final View expenseItemPopupView= getLayoutInflater().inflate(R.layout.expensepopup,null);


        newstartinamount=expenseItemPopupView.findViewById(R.id.itemStartingAmount);
        newexpenseitem=expenseItemPopupView.findViewById(R.id.itemExpense);
        newexpenseamount= expenseItemPopupView.findViewById(R.id.itemamount);
        newexpensedeadlinedate= expenseItemPopupView.findViewById(R.id.expenseDeadline);
        exppercent1 = expenseItemPopupView.findViewById(R.id.exppercent);
        shwsld = expenseItemPopupView.findViewById(R.id.shwslctd);
        data1=new Startsaving(this);

        submitexpense = expenseItemPopupView.findViewById(R.id.btnexpenseadd);
        // cancelexpense= expenseItemPopupView.findViewById(R.id.cancelbtn);


        newexpensedeadlinedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(ExpensesActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                newexpensedeadlinedate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        dialogBuilder.setView(expenseItemPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        submitexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(newexpenseitem.getText())){
                    newexpenseitem.setError("Please enter the expense");
                }
                else if (TextUtils.isEmpty(newstartinamount.getText())){
                    newstartinamount.setError("Please enter your expense total");
                }
                else if (TextUtils.isEmpty(newexpenseamount.getText())){
                    newexpenseamount.setError("Please enter your starting amount");
                }
                else if (TextUtils.isEmpty(newexpensedeadlinedate.getText())){
                    newexpensedeadlinedate.setError("Please select date");
                }

                else {
                    String goal1 = newexpenseitem.getText().toString();
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

                        Boolean test1=data1.saveInfo1(goal1,amount1,startingamount1,newexpensedeadlinedate.getText().toString(),exppercent1.getText().toString(),date3,"UGX");
                        if (test1==true){
                            //Toast.makeText(this,"No Registered Goals Currently. Please Register a goal!!",Toast.LENGTH_SHORT).show();
                           // Toast.makeText(this,"New expense registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ExpensesActivity.this, ExpensesActivity.class);
                            dialog.dismiss();
                            startActivity(intent);
                        }
                        else {
                           //Toast.makeText(this,"Registration Unsuccessful", Toast.LENGTH_LONG).show();
                        }


                        newexpenseitem.getText().clear();
                        newstartinamount.getText().clear();
                        newexpenseamount.getText().clear();
                        newexpensedeadlinedate.getText().clear();

                    }
                }
            }
        });

       /* cancelexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        }); */
    }

    //Retrieve Expenses Method
    private void retrieveExpenses(){
        try{
            Startsaving objdatabaseclass= new Startsaving(this);
            SQLiteDatabase objsqlitedatabase=objdatabaseclass.getReadableDatabase();
            if (objsqlitedatabase!=null){
                Cursor objcursor= objsqlitedatabase.rawQuery("select * from info1 where EPERCENTAGE !='100' ",null);
                if (objcursor.getCount()==0){
                    Toast.makeText(this,"No Registered Expenses Currently. Please add your expenses!!",Toast.LENGTH_SHORT).show();
                }else {
                    while (objcursor.moveToNext()){
                        expensesList.add(new MyexpensesModelClass(objcursor.getString(0)
                                ,objcursor.getString(4),objcursor.getString(3),objcursor.getString(1),objcursor.getString(2),
                                objcursor.getString(5  ),objcursor.getInt(4),objcursor.getString(6),objcursor.getString(6)));
                    }
                    mAdapter = new MyexpensesAdapter(ExpensesActivity.this,expensesList);
                    mRecyclerView.hasFixedSize();
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        }catch (Exception e){
            Toast.makeText(this,"showvalues"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

}