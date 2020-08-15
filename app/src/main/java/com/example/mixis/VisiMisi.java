package com.example.mixis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class VisiMisi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visi_misi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("VisiMisi");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
