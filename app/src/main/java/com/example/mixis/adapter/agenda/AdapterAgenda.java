package com.example.mixis.adapter.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixis.Agenda;
import com.example.mixis.AgendaDetail;
import com.example.mixis.R;

import java.util.List;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder> {

    private List<EntitiyAgenda> agendaList;
    private Agenda mContexts;

    public AdapterAgenda(Agenda mContexts, List<EntitiyAgenda> agendaList){
        this.mContexts = mContexts;
        this.agendaList = agendaList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        //        ini buat variabel biasa
        public TextView judulsagenda,deskripsisagenda,tanggalsagenda;

        public ConstraintLayout clagenda;


        //        ini untuk manggil ID dari XML dan ditampung pada Variabel yang tadi dibuat
        public ViewHolder(View v){
            super(v);
            judulsagenda = (TextView) v.findViewById(R.id.judulagenda);
            deskripsisagenda = (TextView) v.findViewById(R.id.desagenda);
            tanggalsagenda = (TextView) v.findViewById(R.id.tanggalagenda);

            clagenda = (ConstraintLayout)v.findViewById(R.id.consagenda);

        }
    }

    @Override
    public AdapterAgenda.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //ini memanggil list item REcycleerView(XML) dan ditampung pada variabel v
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_agenda,parent,
                false);

        //ini untuk menset VIewHolder dengan item list di atas
        AdapterAgenda.ViewHolder vh = new AdapterAgenda.ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(AdapterAgenda.ViewHolder holder, final int position) {
        //          ini untuk inisialisasi entity
        final EntitiyAgenda agenda = agendaList.get(position);

//             ini untuk mengambil data dari ENtity dan nanti akan diambil dan di set/tampilkan oleh MainACtivity(RecyclerActivity)
        holder.judulsagenda.setText(agenda.getJudul());
        holder.deskripsisagenda.setText(agenda.getDeskripsi());
        holder.tanggalsagenda.setText(agenda.getTanggal());

        holder.clagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
//                    ini ngirim data untuk di ambil pada activity lain
                bundle.putString("judul",agendaList.get(position).getJudul());
                bundle.putString("tgl",agendaList.get(position).getTanggal());
                bundle.putString("desc",agendaList.get(position).getDeskripsi());
                //                    ini pindah activity
                Intent inten = new Intent(view.getContext(), AgendaDetail.class);
                inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inten.putExtras(bundle);

//                    ini untuk menjalankan inten
                mContexts.startActivity(inten);
            }
        });

    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

}
