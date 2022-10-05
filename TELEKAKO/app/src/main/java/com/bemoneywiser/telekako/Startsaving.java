package com.bemoneywiser.telekako;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Startsaving extends SQLiteOpenHelper {

    private Context context;
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "saving.db";
    private static final String TABLE = "info";
    private static final String TABLE1 = "info1";

    public Startsaving(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table info(GOAL varchar(100), AMOUNT varchar(100), STARTINGAMOUNT varchar(100), DATE varchar(100), PERCENTAGE varchar(100), STARTDATE varchar(100), CURRENCY varchar(100))");

        db.execSQL("Create table info1(EXPENSE varchar(100), EAMOUNT varchar(100), SAMOUNT varchar(100), DDATE varchar(100), EPERCENTAGE varchar(100), SDATE varchar(100), NCURRENCY varchar(100))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists info");
        db.execSQL("DROP table if exists info1");
        onCreate(db);
    }
    //    the method to save values
    public boolean saveInfo(String goal, String amount, String startingamount, String date, String percent, String startdate, String currency) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("GOAL", goal);
        values.put("AMOUNT", amount);
        values.put("STARTINGAMOUNT", startingamount);
        values.put("DATE", date);
        values.put("PERCENTAGE", percent);
        values.put("PERCENTAGE", percent);
        values.put("STARTDATE", startdate);
        values.put("CURRENCY", currency);

        long sendData = db.insert(TABLE, null, values);
        if (sendData == -1)
            return false;
        else
            return true;
    }

    public boolean saveInfo1(String expense, String eamount, String samount, String edate, String epercent, String sdate, String scurrency) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("EXPENSE", expense);
        values1.put("EAMOUNT", eamount);
        values1.put("SAMOUNT", samount);
        values1.put("DDATE", edate);
        values1.put("EPERCENTAGE", epercent);
        values1.put("SDATE", sdate);
        values1.put("NCURRENCY", scurrency);

        long sendData = db1.insert(TABLE1, null, values1);
        if (sendData == -1)
            return false;
        else
            return true;
    }


    public void updateInfo(String startingamount,String percent,String goal) {

        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("STARTINGAMOUNT", startingamount);
        value.put("PERCENTAGE", percent);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        long result = db1.update(TABLE, value, "GOAL=?", new String[]{goal});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateExp(String startingamount1,String percent1,String goal) {

        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues value1 = new ContentValues();
        value1.put("SAMOUNT", startingamount1);
        value1.put("EPERCENTAGE", percent1);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        long result = db1.update(TABLE1, value1, "EXPENSE=?", new String[]{goal});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


}
