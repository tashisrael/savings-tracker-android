package com.bemoneywiser.telekako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText fName,lName,email,phoneno,pass,cpass,code,count;
    ProgressDialog progressdialog;
    Spinner dropdown,dropdown1;
    String captureselected,captureselected1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        fName = findViewById(R.id.fname);
        lName = findViewById(R.id.lname);
        email = findViewById(R.id.Email);
        phoneno = findViewById(R.id.PhoneNumber);
        pass = findViewById(R.id.yourpassword);
        cpass = findViewById(R.id.confpassword);
        code=findViewById(R.id.code);
        count=findViewById(R.id.count);



        dropdown = findViewById(R.id.spinnerCountry);
        //create a list of items for the spinner
        String[] items = new String[]{
    "Afghanistan","Albania"
    ,"Algeria", "American Samoa"
    ,"Andorra","Angola","Anguilla","Antarctica", "Antigua and Barbuda"
    ,"Argentina","Armenia", "Aruba","Australia"
    ,"Austria","Azerbaijan"
    ,"Bahrain"
   ,"Bangladesh"
   ,"Barbados"
    ,"Belarus"
    ,"Belgium"
  ,"Belize"
    ,"Benin"
    ,"Bermuda"
    ,"Bhutan"
    ,"Bolivia"
    ,"Bosnia and Herzegovina"
    ,"Botswana"
   ,"Bouvet Island"
   ,"Brazil"
 ,"British Indian Ocean Territory"
    ,"British Virgin Islands"
    ,"Brunei"
    ,"Bulgaria"
    ,"Burkina Faso"
   ,"Burundi"
   ,"Cambodia"
    ,"Cameroon"
    ,"Canada"
    ,"Cape Verde"
    ,"Cayman Islands"
    ,"Central African Republic"
    ,"Chad"
    ,"Chile"
    ,"China"
    ,"Christmas Island"
    ,"Cocos Islands"
    ,"Colombia"
    ,"Comoros"
    ,"Congo"
    ,"Cook Islands"
    ,"Costa Rica"
    ,"Cote d\'Ivoire","Croatia"
    ,"Cuba"
    ,"Cyprus"
    ,"Czech Republic"
    ,"Democratic Republic of the Congo"
    ,"Denmark"
    ,"Djibouti"
    ,"Dominica"
    ,"Dominican Republic"
    ,"East Timor"
    ,"Ecuador"
    ,"Egypt"
    ,"El Salvador"
    ,"Equatorial Guinea"
    ,"Eritrea"
    ,"Estonia"
    ,"Ethiopia"
    ,"Faeroe Islands"
    ,"Falkland Islands"
    ,"Fiji"
    ,"Finland"
    ,"Former Yugoslav Republic of Macedonia"
    ,"France"
    ,"French Guiana"
    ,"French Polynesia"
    ,"French Southern Territories"
    ,"Gabon"
    ,"Georgia"
    ,"Germany"
    ,"Ghana"
    ,"Gibraltar"
    ,"Greece"
    ,"Greenland"
    ,"Grenada"
    ,"Guadeloupe"
    ,"Guam"
    ,"Guatemala"
    ,"Guinea"
    ,"Guinea-Bissau"
    ,"Guyana"
    ,"Haiti"
    ,"Heard Island and McDonald Islands"
    ,"Honduras"
    ,"Hong Kong"
    ,"Hungary"
    ,"Iceland"
    ,"India"
    ,"Indonesia"
    ,"Iran"
    ,"Iraq"
    ,"Ireland"
    ,"Israel"
    ,"Italy"
    ,"Jamaica"
    ,"Japan"
    ,"Jordan"
    ,"Kazakhstan"
    ,"Kenya"
    ,"Kiribati"
    ,"Kuwait"
    ,"Kyrgyzstan"
    ,"Laos"
    ,"Latvia"
    ,"Lebanon"
    ,"Lesotho"
    ,"Liberia"
    ,"Libya"
    ,"Liechtenstein"
    ,"Lithuania"
    ,"Luxembourg"
    ,"Macau"
    ,"Madagascar"
    ,"Malawi"
    ,"Malaysia"
    ,"Maldives"
    ,"Mali"
    ,"Malta"
    ,"Marshall Islands"
    ,"Martinique"
    ,"Mauritania"
    ,"Mauritius"
    ,"Mayotte"
    ,"Mexico"
    ,"Micronesia"
    ,"Moldova"
    ,"Monaco"
    ,"Mongolia"
    ,"Montenegro"
    ,"Montserrat"
    ,"Morocco"
    ,"Mozambique"
    ,"Myanmar"
    ,"Namibia"
    ,"Nauru"
    ,"Nepal"
    ,"Netherlands"
    ,"Netherlands Antilles"
    ,"New Caledonia"
    ,"New Zealand"
    ,"Nicaragua"
    ,"Niger"
    ,"Nigeria"
    ,"Niue"
    ,"Norfolk Island"
    ,"North Korea"
    ,"Northern Marianas"
    ,"Norway"
    ,"Oman"
    ,"Pakistan"
    ,"Palau"
    ,"Panama"
    ,"Papua New Guinea"
    ,"Paraguay"
    ,"Peru"
    ,"Philippines"
    ,"Pitcairn Islands"
    ,"Poland"
    ,"Portugal"
    ,"Puerto Rico"
    ,"Qatar"
    ,"Reunion"
    ,"Romania"
   ,"Russia"
    ,"Rwanda"
    ,"Sqo Tome and Principe"
    ,"Saint Helena"
    ,"Saint Kitts and Nevis"
    ,"Saint Lucia"
    ,"Saint Pierre and Miquelon"
    ,"Saint Vincent and the Grenadines"
    ,"Samoa"
    ,"San Marino"
    ,"Saudi Arabia"
    ,"Senegal"
    ,"Serbia"
    ,"Seychelles"
    ,"Sierra Leone"
    ,"Singapore"
    ,"Slovakia"
    ,"Slovenia"
    ,"Solomon Islands"
    ,"Somalia"
    ,"South Africa"
    ,"South Georgia and the South Sandwich Islands"
    ,"South Korea"
    ,"South Sudan"
   ,"Spain"
    ,"Sri Lanka"
    ,"Sudan"
    ,"Suriname"
    ,"Svalbard and Jan Mayen"
    ,"Swaziland"
    ,"Sweden"
    ,"Switzerland"
    ,"Syria"
        ,"Taiwan"
    ,"Tajikistan"
    ,"Tanzania"
    ,"Thailand"
    ,"The Bahamas"
    ,"The Gambia"
    ,"Togo"
    ,"Tokelau"
    ,"Tonga"
    ,"Trinidad and Tobago"
    ,"Tunisia"
    ,"Turkey"
    ,"Turkmenistan"
    ,"Turks and Caicos Islands"
    ,"Tuvalu"
    ,"Virgin Islands"
    ,"Uganda"
    ,"Ukraine"
    ,"United Arab Emirates"
    ,"United Kingdom"
    ,"United States"
    ,"United States Minor Outlying Islands"
    ,"Uruguay"
    ,"Uzbekistan"
    ,"Vanuatu"
    ,"Vatican City"
    ,"Venezuela"
    ,"Vietnam"
    ,"Wallis and Futuna"
    ,"Western Sahara"
    ,"Yemen"
    ,"Yugoslavia"
    ,"Zambia"
    ,"Zimbabwe"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected=(String) parent.getItemAtPosition(position);
                count.setText(captureselected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       dropdown1 = findViewById(R.id.spinnerCodes);
        //create a list of items for the spinner
        String[] items2 = new String[]{"+256","+255","+254","+253","+1","+49","+33","+27","+234","+55","+91","+251","+233"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items2);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter2);

        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captureselected1=(String) parent.getItemAtPosition(position);
                code.setText(captureselected1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void register(View view) {
        String Fname = fName.getText().toString().trim();
        String Lname = lName.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String PhoneNum = phoneno.getText().toString().trim();
        String Pass = pass.getText().toString().trim();
        String cPass = cpass.getText().toString().trim();
        String count1 = count.getText().toString().trim();
        String code1 = code.getText().toString().trim();

        if (Fname.isEmpty()){
            fName.setError("FirstName Required!");
            fName.requestFocus();
            return;
        }
        else if (Lname.isEmpty()){
            lName.setError("LastName Required!");
            lName.requestFocus();
            return;
        }
        else if (Email.isEmpty()){
            email.setError("Email Required!");
            email.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Please enter a valid email");
            email.requestFocus();
        }

        else if (PhoneNum.isEmpty()){
            phoneno.setError("PhoneNumber Required!");
            email.requestFocus();
            return;
        }
        else if (Pass.length() < 6){
            pass.setError("Password should be above 6 characters!");
            pass.requestFocus();
            return;
        }

        else if (!cPass.equals(Pass)){
            cpass.setError("Passwords don't match");
            cpass.requestFocus();
            return;
        }
        else {

            mAuth.createUserWithEmailAndPassword(Email, Pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                User user = new User(Fname, Lname, Email, PhoneNum, Pass, cPass, count1,code1);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                                            Toast.makeText(SignUpActivity.this, "Registered Successfully! Now LOGIN", Toast.LENGTH_LONG).show();
                                            startActivity(intent);

                                            fName.getText().clear();
                                            lName.getText().clear();
                                            email.getText().clear();
                                            phoneno.getText().clear();
                                            pass.getText().clear();
                                            cpass.getText().clear();

                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Failed registration, Please connect to the internet and try again", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignUpActivity.this, "Email already exists or Connect to the internet please!!", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }


    }

    public void log(View view) {
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}