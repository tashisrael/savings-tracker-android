package com.bemoneywiser.telekako;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User1Crud {

    private DatabaseReference databaseReference;

    public User1Crud(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User1.class.getSimpleName());
    }
    public Task<Void> add(User1 users1){
       return databaseReference.push().setValue(users1);
    }
}
