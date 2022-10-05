package com.bemoneywiser.telekako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MySavingsFile extends AppCompatActivity {
         private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
   ArrayList<MysavingsModelClass> savingsList;


    ImageView leftimage , rightimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_savings_file);


       /* user2=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID1 = user2.getUid();

        email1 = findViewById(R.id.showemail2);

        reference.child(userID1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String Email = userProfile.Email;

                    email1.setText(Email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MySavingsFile.this,"Connect to internet! Something went wrong",Toast.LENGTH_LONG).show();

            }
        });*/


        //String ema = email1.getText().toString();

       savingsList = new ArrayList<>();


          mRecyclerView= findViewById(R.id.recyclermysavings);
          retrieveSavings();

       // String Email1 = email1.getText().toString().trim();

      /*  FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("User1").orderByChild("email1").equalTo("tashisrael04@gmail.com"), MainModel.class)
                .build();


        mainAdapter = new MainAdapter(options, context);
        mRecyclerView.setAdapter(mainAdapter);*/


         /* mRecyclerView.setHasFixedSize(true);
          mLayoutManager = new LinearLayoutManager(this);
          mAdapter = new MySavingsAdapter(MySavingsFile.this,savingsList);

          mRecyclerView.setLayoutManager(mLayoutManager);
          mRecyclerView.setAdapter(mAdapter);  */

        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MySavingsFile.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MySavingsFile.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }*/

     private void retrieveSavings(){
       try{

           Startsaving objdatabaseclass= new Startsaving(this);
           SQLiteDatabase objsqlitedatabase=objdatabaseclass.getReadableDatabase();
           if (objsqlitedatabase!=null){
               Cursor objcursor= objsqlitedatabase.rawQuery("select * from info where PERCENTAGE !='100' ",null);
               if (objcursor.getCount()==0){
                   Toast.makeText(this,"No Registered Goals Currently. Please Register a goal!!",Toast.LENGTH_SHORT).show();
               }else {
                   while (objcursor.moveToNext()){
                      savingsList.add(new MysavingsModelClass(objcursor.getString(0)
                              ,objcursor.getString(4),objcursor.getString(3)
                              ,objcursor.getString(1),objcursor.getString(2)
                              ,objcursor.getString(5  ),objcursor.getInt(4)
                              ,objcursor.getString(6),objcursor.getString(6)));
                       //String resultsw = objcursor.getString(4);
                       //ProgressBar bar = findViewById(R.id.progress1);
                      // bar.setProgress(Integer.parseInt(resultsw));
                   }
                   mAdapter = new MySavingsAdapter(MySavingsFile.this,savingsList);
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