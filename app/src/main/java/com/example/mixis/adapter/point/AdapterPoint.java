package com.example.mixis.adapter.point;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixis.R;
import com.example.mixis.TukarPoint;
import com.example.mixis.TukarPointDetail;

import java.util.List;

public class AdapterPoint extends RecyclerView.Adapter<AdapterPoint.ViewHolder> {

    private List<EntityPoint> pointList;
    private TukarPoint mContext;

    public AdapterPoint(TukarPoint mContext, List<EntityPoint> pointList){
        this.mContext = mContext;
        this.pointList = pointList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tJudul, tTanggal, tDeskripsi,tpoint;

        CardView cv;


        //        ini untuk manggil ID dari XML dan ditampung pada Variabel yang tadi dibuat
        public ViewHolder(View v){
            super(v);
            tJudul = (TextView)v.findViewById(R.id.TV_promo);
            tDeskripsi = (TextView)v.findViewById(R.id.TV_deskripsiPromo);
            tpoint = (TextView)v.findViewById(R.id.TV_pointPromo);
            tTanggal = (TextView)v.findViewById(R.id.TV_tanggalPromo);

            cv = (CardView) v.findViewById(R.id.cardpromo);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //ini memanggil list item REcycleerView(XML) dan ditampung pada variabel v
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_promos,parent,
                false);

        //ini untuk menset VIewHolder dengan item list di atas
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        EntityPoint promos =pointList.get(position);

        holder.tJudul.setText(String.valueOf(promos.getJudul()));
        holder.tDeskripsi.setText(String.valueOf(promos.getDeskripsi()));
        holder.tpoint.setText(String.valueOf(promos.getPoint()));
        holder.tTanggal.setText(String.valueOf(promos.getDate()));


        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(mContext, TukarPointDetail.class);

//                    ini ngirim data untuk di ambil pada activity lain
                inten.putExtra("judul",pointList.get(position).getJudul());
                inten.putExtra("deskripsi",pointList.get(position).getDeskripsi());
                inten.putExtra("point",pointList.get(position).getPoint());
                inten.putExtra("tanggal",pointList.get(position).getDate());

//                    ini untuk menjalankan inten
                mContext.startActivity(inten);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pointList.size();
    }


}
