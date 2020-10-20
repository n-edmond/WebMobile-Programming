package com.example.icp8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
        }
    public void redirectToHomePage(View view){
        Intent redirect = new Intent (HomeActivity.this, MainActivity.class);
        startActivity(redirect);
    }
    public void goBack(View view) {//take back to home page
            //validationFlag = false;
        Toast.makeText(getApplicationContext(), "Logging out...", Toast.LENGTH_SHORT).show();
            redirectToHomePage(view);
    }

    }


