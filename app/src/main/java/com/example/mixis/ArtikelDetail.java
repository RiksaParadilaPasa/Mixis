package com.example.mixis;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArtikelDetail extends AppCompatActivity {

    public TextView judul,tanggal,deskripsi;
    public ImageView gams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Artikel");

        judul = (TextView)findViewById(R.id.judulartikelactivity);
        tanggal = (TextView)findViewById(R.id.tanggalartikelactivity);
        deskripsi = (TextView)findViewById(R.id.deskripsiartikelactivity);
        gams = (ImageView)findViewById(R.id.ivartikelactivity);


//        ini untuk mengambil data yg dikirim dari ADAPTER (ONBINDVIEWHOLDER)


                judul.setText(getIntent().getStringExtra("judul"));
                tanggal.setText(getIntent().getStringExtra("tgl"));
                deskripsi.setText(getIntent().getStringExtra("desc"));
//                gams.setImageResource(Integer.parseInt(String.valueOf(getIntent().getStringExtra    ("img"))));


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
