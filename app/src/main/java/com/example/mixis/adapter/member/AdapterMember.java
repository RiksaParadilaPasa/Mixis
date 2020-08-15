package com.example.mixis.adapter.member;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mixis.Mixi;
import com.example.mixis.R;

import java.util.List;

public class AdapterMember extends RecyclerView.Adapter<AdapterMember.ViewHolder> {

    private List<EntityMember> partnerHomeListList;
    private Mixi mContext;

    public AdapterMember(Mixi mContext, List<EntityMember> partnerHomeListList){
        this.mContext = mContext;
        this.partnerHomeListList = partnerHomeListList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        //        ini buat variabel biasa
        public ImageView imgpartner;


        //        ini untuk manggil ID dari XML dan ditampung pada Variabel yang tadi dibuat
        public ViewHolder(View v){
            super(v);
            imgpartner = (ImageView) v.findViewById(R.id.imagepartnerhome);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //ini memanggil list item REcycleerView(XML) dan ditampung pada variabel v
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_partner_mixi,parent,
                false);

        //ini untuk menset VIewHolder dengan item list di atas
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//          ini untuk inisialisasi entity
        EntityMember partnerHome = partnerHomeListList.get(position);

//             ini untuk mengambil data dari ENtity dan nanti akan diambil dan di set/tampilkan oleh MainACtivity(RecyclerActivity)
        holder.imgpartner.setImageResource(partnerHome.getImage());

    }

    @Override
    public int getItemCount() {
        return partnerHomeListList.size();
    }


}
