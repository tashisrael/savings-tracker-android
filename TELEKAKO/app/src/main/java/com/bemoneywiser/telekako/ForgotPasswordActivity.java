package com.bemoneywiser.telekako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText email1;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email1=findViewById(R.id.emailadd);
        auth = FirebaseAuth.getInstance();
    }

    public void reset(View view) {
        String email = email1.getText().toString().trim();

        if (email.isEmpty()){
            email1.setError("Please enter your email");
            email1.requestFocus();
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email1.setError("Please enter a valid email");
            email1.requestFocus();
        }
        else {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ForgotPasswordActivity.this,"Check your email to reset password", Toast.LENGTH_LONG).show();
                        email1.getText().clear();
                    }
                    else {
                        Toast.makeText(ForgotPasswordActivity.this,"Something is wrong!! Try again.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}