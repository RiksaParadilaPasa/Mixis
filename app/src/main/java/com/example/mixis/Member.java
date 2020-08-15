package com.example.mixis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Member");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
