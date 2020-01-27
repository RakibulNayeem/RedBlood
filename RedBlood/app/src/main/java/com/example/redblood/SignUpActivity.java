package com.example.redblood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText nameEditText,emailEditText,passwordEditText,usernameEditText,bloodGroupEditText,genderEditText,mobileNumberEditText;
    private EditText addressEditText;
    private Button signInButton,signUpButton;
    UserDetails userDetails;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = (EditText) findViewById(R.id.nameEditTextId);
        emailEditText = (EditText) findViewById(R.id.emailEditTextId);
        passwordEditText = (EditText) findViewById(R.id.passwordEditTextId);
        usernameEditText = (EditText) findViewById(R.id.userNameEditTextId);
        bloodGroupEditText = (EditText) findViewById(R.id.bloodGroupeEditTextId);
        genderEditText = (EditText) findViewById(R.id.genderEditTextId);
        mobileNumberEditText = (EditText) findViewById(R.id.mobileNumberEditTextId);
        addressEditText = (EditText) findViewById(R.id.addressEditTextId);


        signInButton = (Button) findViewById(R.id.signInButtonId);
        signUpButton = (Button) findViewById(R.id.signUpButtonId);

        userDetails = new UserDetails();
        databaseHelper = new DatabaseHelper(this);

        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {


        if(v.getId()==R.id.signUpButtonId){


            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            String blood_group = bloodGroupEditText.getText().toString();
            String gender = genderEditText.getText().toString();
            String mobile_number = mobileNumberEditText.getText().toString();
            String address = addressEditText.getText().toString();



            userDetails.setName(name);
            userDetails.setEmail(email);
            userDetails.setPassword(password);
            userDetails.setUsername(username);
            userDetails.setBlood_group(blood_group);
            userDetails.setGender(gender);
            userDetails.setPhone_number(mobile_number);
            userDetails.setAddress(address);


            long rowid =  databaseHelper.insertData(userDetails);

            if(rowid>-1){
                Toast.makeText(getApplicationContext(),"Information saved successfully",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignUpActivity.this,ListDataActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"Information insertion failed",Toast.LENGTH_SHORT).show();
            }

        }


        else if(v.getId()==R.id.signInButtonId){
            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intent);
        }


    }
}
