package com.bemoneywiser.telekako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
     Button btnsignin,btnlogin;
    private FirebaseAuth mAuth;
    EditText email,pass;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignin=findViewById(R.id.signupbtn);
        btnlogin= findViewById(R.id.loginbtn);

        /*btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,HomeDashboard.class);
                startActivity(intent);
            }
        });*/

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Uemailaddress);
        pass = findViewById(R.id.Upassword);


        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


    }

    public void login(View view) {
        String Email = email.getText().toString().trim();
        String Pass = pass.getText().toString().trim();

        if (Email.isEmpty()){
            email.setError("Please Enter Email!");
            email.requestFocus();
            return;
        }

        if (Pass.isEmpty()){
            pass.setError("Please Enter Your Password!");
            pass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Intent intent=new Intent(LoginActivity.this,HomeDashboard.class);
                   progressdialog = new ProgressDialog(LoginActivity.this);
                   progressdialog.show();
                   progressdialog.setContentView(R.layout.signingin);
                   progressdialog.getWindow().setBackgroundDrawableResource(
                           android.R.color.transparent
                   );
                   startActivity(intent);

               }
               else {
                   Toast.makeText(LoginActivity.this,"Login Failed! Connect to Internet or Wrong Login credentials",Toast.LENGTH_LONG).show();
               }
            }
        });

    }

    public void resetpwd(View view) {
        Intent intent=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
}
