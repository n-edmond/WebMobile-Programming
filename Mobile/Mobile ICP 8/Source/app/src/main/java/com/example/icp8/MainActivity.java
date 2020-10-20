package com.example.icp8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;//adding edittext widget
import android.widget.Toast; //allows messages to display to the user
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void redirectToHomePage(View view){//this function redirects to the home page
        Intent redirect = new Intent (MainActivity.this, HomeActivity.class);
        startActivity(redirect);
    }

    public void click(View view){
        EditText userNameTemp = (EditText) findViewById(R.id.editText2);
        EditText passwordTemp = (EditText) findViewById(R.id.editText1);

        String userName = userNameTemp.getText().toString(); //retrieves username
        String password = passwordTemp.getText().toString(); //retrieves password

        boolean validationFlag = false;//used to check if the user name and password are correct and filled in

        //this will check that the username and password fields are not empty.

        if (!userName.isEmpty() && !password.isEmpty()) {
            if (userName.equals("Admin") && password.equals("Admin")) { //if the password and username are correct, set flag to  true
                validationFlag = true;
            }
        }

        if (!validationFlag) {//when the flag is false notify user that something is wrong
            Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();

        } else {//when the flag is true redirect to new page
            Toast.makeText(getApplicationContext(), "Redirecting....", Toast.LENGTH_SHORT).show();
            redirectToHomePage(view);
        }
    }//end of function
    }
