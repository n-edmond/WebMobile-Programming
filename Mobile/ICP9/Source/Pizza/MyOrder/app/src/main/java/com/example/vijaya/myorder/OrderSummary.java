package com.example.vijaya.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OrderSummary extends AppCompatActivity {

    public void back(View view){
        Intent redirect = new Intent(OrderSummary.this, MainActivity.class);
        startActivity(redirect);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
