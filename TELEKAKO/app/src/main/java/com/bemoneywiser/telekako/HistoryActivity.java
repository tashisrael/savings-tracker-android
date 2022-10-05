package com.bemoneywiser.telekako;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<MysavingsModelClass> savingsList;


    ImageView leftimage , rightimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        savingsList = new ArrayList<>();

        mRecyclerView= findViewById(R.id.recyclermysavings);
        retrieveSavings();



        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HistoryActivity.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HistoryActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveSavings(){
        try{
            Startsaving objdatabaseclass= new Startsaving(this);
            SQLiteDatabase objsqlitedatabase=objdatabaseclass.getReadableDatabase();
            if (objsqlitedatabase!=null){
                Cursor objcursor= objsqlitedatabase.rawQuery("select * from info where PERCENTAGE ='100' ",null);
                if (objcursor.getCount()==0){
                    Toast.makeText(this,"No Completed Goals Currently!!!",Toast.LENGTH_SHORT).show();
                }else {
                    while (objcursor.moveToNext()){
                        savingsList.add(new MysavingsModelClass(objcursor.getString(0)
                                ,objcursor.getString(4),objcursor.getString(3),objcursor.getString(1),objcursor.getString(2),objcursor.getString(5  ),objcursor.getInt(4),objcursor.getString(6),objcursor.getString(6)));
                        //String resultsw = objcursor.getString(4);
                        //ProgressBar bar = findViewById(R.id.progress1);
                        // bar.setProgress(Integer.parseInt(resultsw));
                    }
                    mAdapter = new MySavingsAdapter(HistoryActivity.this,savingsList);
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