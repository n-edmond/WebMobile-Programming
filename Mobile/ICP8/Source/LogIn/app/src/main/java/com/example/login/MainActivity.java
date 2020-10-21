package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;//for changing background color
import android.content.Intent;//for redirecting to new page
import android.view.View; //for functions
import android.widget.EditText; //for username/password
import android.widget.RelativeLayout; //for color change
import android.widget.Toast;//used to display message to users

public class MainActivity extends AppCompatActivity {

    public  void myLogin(View view){

        EditText getUserName = (EditText) findViewById(R.id.username);
        EditText getPassword = (EditText) findViewById(R.id.password);

        String username = getUserName.getText().toString(); //used to convert username to string
        String password = getPassword.getText().toString(); //used to convert password to string

        boolean validation = false; //used to validate user log in

        if(!username.isEmpty() && !password.isEmpty()){//if the password and username is correct set flag to true
            if(username.equals("Admin") && password.equals("Admin")){
                validation = true;
            }
        }

        if(!validation){//if the flag is not true, notify user that something is wrong
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
        }
        else{
            Intent redirect = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(redirect);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //changing color of background to light gray here
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(Color.LTGRAY);
    }
}