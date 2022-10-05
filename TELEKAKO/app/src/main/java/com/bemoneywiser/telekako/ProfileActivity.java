package com.bemoneywiser.telekako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    ImageView leftimage , rightimage;
    TextView completedGoals;

    private FirebaseUser user1;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //load the completed goals activity
        completedGoals=findViewById(R.id.completedGoals);
        completedGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });



        user1=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID = user1.getUid();

        final TextView fullname = findViewById(R.id.userfullname);
        final TextView email = findViewById(R.id.profemail);
        final TextView phoneNo = findViewById(R.id.profcontact);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String Fname = userProfile.Fname;
                    String Lname = userProfile.Lname;
                    String Email = userProfile.Email;
                    String PhoneNum = userProfile.PhoneNum;

                    fullname.setText(Fname + " " + Lname);
                    email.setText(Email);
                    phoneNo.setText(PhoneNum);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Something wrong happened",Toast.LENGTH_LONG).show();

            }
        });



        leftimage=findViewById(R.id.left_icon);
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
        rightimage=findViewById(R.id.right_icon);
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void share(View view) {
       try{ Intent i=new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "TELEKAKO APP!");
        String shareMessage = "\nPlease Download This App To Help You Track Your Savings\n\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
        i.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(i,"Share via"));
       }
       catch (Exception e){

       }
    }

    public void rate(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=")));
    }

    public boolean feedback(View view) {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/plain");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "tashisrael04@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback on TelekakoApp");
        Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
        startActivity(Intent.createChooser(Email, "Send Feedback:"));
        return true;
    }

    public void contact(View view) {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/plain");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "bemoneywiser@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "I would like to inquire about Telekako! Save Some Mo!");
        Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
        startActivity(Intent.createChooser(Email, "Contact Us Directly:"));

    }
}