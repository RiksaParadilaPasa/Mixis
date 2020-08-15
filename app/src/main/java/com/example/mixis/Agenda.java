package com.example.mixis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixis.adapter.agenda.AdapterAgenda;
import com.example.mixis.adapter.agenda.EntitiyAgenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<EntitiyAgenda> agendaList;
    private AdapterAgenda adapterAgenda;


    String[] juds = {
            "touring",
            "bakti sosial",
            "silaturahmi",
            "rapat",
            "camp"
    };

    String[] descs = {
            "touring dari bandung menuju masa depan yang cerah",
            "bakti sosial kepada seluruh masyarakt Indonesia",
            "silaturahmi sesama anggota MIXI dan kepada orang sekitar",
            "rapat umum untuk membicarakan tentang kegiatan selanjutnya",
            "camp di gunung untuk mencintai alam Indonesia"
    };

    String[] tanggals = {
            "2019/08/01",
            "2019/09/11",
            "2019/09/31",
            "2019/10/01",
            "2019/11/21"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Agenda");


        mRecyclerView = (RecyclerView)findViewById(R.id.recyleragenda);
        agendaList = new ArrayList<>();

        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        agendaList = new ArrayList<>();

        for (int i = 0; i < juds.length; i++){
            EntitiyAgenda agenda = new EntitiyAgenda(juds[i],descs[i],tanggals[i]);
            agendaList.add(agenda);
        }

        adapterAgenda = new AdapterAgenda(this,agendaList);

        //specifying an adapter to acces data, create views and replace the content

        mRecyclerView.setAdapter(adapterAgenda);
        adapterAgenda.notifyDataSetChanged();


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
