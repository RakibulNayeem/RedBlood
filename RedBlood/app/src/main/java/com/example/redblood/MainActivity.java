package com.example.redblood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseHelper databaseHelper;
    private EditText usernameEditText,passwordEditText;
    private Button signInButton,signUpHereButton,skipButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameEditText = (EditText) findViewById(R.id.signInusernameEditTextId);
        passwordEditText = (EditText) findViewById(R.id.signInPasswordEditTextId);

        signInButton = (Button) findViewById(R.id.signInButtonId);
        signUpHereButton = (Button) findViewById(R.id.signUpHereButtonId);
        skipButton = (Button) findViewById(R.id.skipButtonId);

        signUpHereButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        skipButton.setOnClickListener(this);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();



    }

    @Override
    public void onClick(View v) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(v.getId()==R.id.signInButtonId){

            Boolean result = databaseHelper.findPassword(username,password);
            if(result == true){

                Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"Username or password is incorrect",Toast.LENGTH_SHORT).show();
            }


        }

        else if(v.getId()==R.id.signUpHereButtonId){

            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);
        }


        else if(v.getId()==R.id.skipButtonId){

            Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
            startActivity(intent);
        }


    }



}
