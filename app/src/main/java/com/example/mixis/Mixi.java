package com.example.mixis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixis.adapter.mixi.AdapterPartnerHome;
import com.example.mixis.adapter.mixi.EntityPartnerHome;

import java.util.ArrayList;
import java.util.List;

public class Mixi extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<EntityPartnerHome> partnerHomeList;
    private AdapterPartnerHome adapterPartnerHome;


    int[] pics = {
            R.drawable.agp,
            R.drawable.danamon,
            R.drawable.mitsubishi,
            R.drawable.mldspot,
            R.drawable.sariater

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mixi");


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerhomepartner);
        partnerHomeList = new ArrayList<>();

        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(Mixi.this,LinearLayoutManager.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        partnerHomeList = new ArrayList<>();

        for (int i = 0; i < pics.length; i++){
            EntityPartnerHome partnerHome = new EntityPartnerHome(pics[i]);
            partnerHomeList.add(partnerHome);
        }

        adapterPartnerHome = new AdapterPartnerHome(this,partnerHomeList);

        //specifying an adapter to acces data, create views and replace the content

        mRecyclerView.setAdapter(adapterPartnerHome);
        adapterPartnerHome.notifyDataSetChanged();

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
