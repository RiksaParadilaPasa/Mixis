package com.example.mixis;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Array of choices
        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};
        // Selection of the spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Jual Barang");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
