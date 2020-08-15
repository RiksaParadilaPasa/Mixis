package com.example.mixis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixis.adapter.point.AdapterPoint;
import com.example.mixis.adapter.point.EntityPoint;

import java.util.ArrayList;
import java.util.List;

public class TukarPoint extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<EntityPoint> pointList;
    private AdapterPoint adapterPoint;


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

    String[] points = {
            "500",
            "1000",
            "1500",
            "2000",
            "2500"
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
        setContentView(R.layout.activity_tukar_point);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("TukarPoint");

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerpoint);
        pointList = new ArrayList<>();

        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        pointList = new ArrayList<>();

        for (int i = 0; i < juds.length; i++){
            EntityPoint point = new EntityPoint(juds[i],descs[i],points[i],tanggals[i]);
            pointList.add(point);
        }

        adapterPoint = new AdapterPoint(this,pointList);

        //specifying an adapter to acces data, create views and replace the content

        mRecyclerView.setAdapter(adapterPoint);
        adapterPoint.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
