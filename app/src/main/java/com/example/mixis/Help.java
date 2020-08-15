package com.example.mixis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bantuan");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
