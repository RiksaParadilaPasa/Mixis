package com.example.mixis;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AgendaDetail extends AppCompatActivity {

    public TextView judul,tanggal,deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_detail);

        judul = (TextView)findViewById(R.id.judulagendaactivity);
        tanggal = (TextView)findViewById(R.id.tanggalagendaactivity);
        deskripsi = (TextView)findViewById(R.id.deskripsiagendaactivity);


//        ini untuk mengambil data yg dikirim dari ADAPTER (ONBINDVIEWHOLDER)

        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();

            if(bundle != null){
                judul.setText(bundle.getString("judul"));
                tanggal.setText(bundle.getString("tgl"));
                deskripsi.setText(bundle.getString("desc"));

            } else{
                judul.setText(getIntent().getStringExtra("judul"));
                tanggal.setText(getIntent().getStringExtra("tgl"));
                deskripsi.setText(getIntent().getStringExtra("desc"));
            }

        }

    }
}
