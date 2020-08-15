package com.example.mixis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TentangKami extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("TentangKami");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
