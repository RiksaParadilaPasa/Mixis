package com.example.mixis.adapter.artikel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixis.ArtikelDetail;
import com.example.mixis.Login;
import com.example.mixis.R;
import com.example.mixis.api.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.ViewHolder> {


    private List<EntitiyArtikel> artikelList;
    private Context mContext;

    String juduls,tanggals,deskripsis;
    int imgs;

    String resultToken, resultHeader;

    Bundle extras;

    View view;

    private String token = null;
    private String id = null;

    BaseApiService mApiService;

    public AdapterArtikel(List<EntitiyArtikel> artikelList, Context mContext) {
        this.artikelList = artikelList;
        this.mContext = mContext;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView judulsartikel,deskripsisartikel,tanggalsartikel;
        public ImageView gambarartikel;
        public CardView cr;

        public ViewHolder( View v) {
            super(v);
            judulsartikel = (TextView) v.findViewById(R.id.TV_artikel);
            deskripsisartikel = (TextView) v.findViewById(R.id.TV_deskripsiartikel);
            tanggalsartikel = (TextView) v.findViewById(R.id.TV_tanggalartikel);
            gambarartikel = (ImageView) v.findViewById(R.id.IV_artikel);


            cr = (CardView)v.findViewById(R.id.cardartikel);

        }
    }



    @Override
    public AdapterArtikel.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_artikel,parent,false);
        ViewHolder vh = new ViewHolder(v);



        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterArtikel.ViewHolder holder, final int position) {
        EntitiyArtikel artikel = artikelList.get(position);

        holder.judulsartikel.setText(String.valueOf(artikel.getTitle()));
        holder.deskripsisartikel.setText(String.valueOf(artikel.getDescription()));
        holder.tanggalsartikel.setText(String.valueOf(artikel.getDate()));
//        holder.gambarartikel.setImageResource(artikel.getImage());

        holder.cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(view.getContext(), ArtikelDetail.class);
//                    ini ngirim data untuk di ambil pada activity lain
                inten.putExtra("judul",artikelList.get(position).getTitle());
                inten.putExtra("tgl",artikelList.get(position).getDate());
                inten.putExtra("desc",artikelList.get(position).getDescription());
//                bundle.putInt("img", artikelList.get(position).getImage());
                //                    ini pindah activity



//                    ini untuk menjalankan inten
                view.getContext().startActivity(inten);
            }
        });

        holder.cr.setOnLongClickListener(new View.OnLongClickListener() {


            @Override
            public boolean onLongClick(View view) {

                EntitiyArtikel artikel = artikelList.get(position);
                String id = artikel.getId();
                juduls = artikel.getTitle();
                deskripsis = artikel.getDescription();
                imgs = artikel.getImage();
                tanggals = artikel.getDate();
                showDialog(id,tanggals);

                return true;
            }
        });

        
    }


    @Override
    public int getItemCount() {
        return artikelList.size();
    }


    public void showDialog(final String id, final String tanggals){
        if(mContext != null) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View dialogView = inflater.inflate(R.layout.layout_choose_artikel, null);
            dialogBuilder.setView(dialogView);

            Button btnDoUpdate = (Button) dialogView.findViewById(R.id.btn_edit_artikel);
            Button btnDoDelete = (Button) dialogView.findViewById(R.id.btn_del_artikel);

            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();

            btnDoUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialogUpdateInfo(id,tanggals);
                    alertDialog.dismiss();
                }
            });

            btnDoDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteartikel(id);
                    alertDialog.dismiss();
                }
            });
        }
    }

    private void deleteartikel(String id) {

        this.token = Login.token;

//        resultToken = "Bearer "+ extras.getString("Token");
        resultHeader = "application/json";

        mApiService.DeleteArtikelRequest(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()){
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());

                        Log.d("Session ", "");
                        Toast.makeText(mContext, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("Session ", "");
                        Toast.makeText(mContext, "gagal Hapus Data", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Session ", "");
            }
        });



    }


    private void showDialogUpdateInfo(String id , String tanggals) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View dialogView = inflater.inflate(R.layout.layout_update_artikel,null);
        dialogBuilder.setView(dialogView);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.btnartikelupdated);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etjudul = (EditText)dialogView.findViewById(R.id.artikeljudulupdated);
        etjudul.setText(juduls);
        final EditText etdes = (EditText)dialogView.findViewById(R.id.artikeldeskripsiupdated);
        etdes.setText(deskripsis);
        final EditText img = (EditText)dialogView.findViewById(R.id.artikelimageupdated);
        img.setText(imgs);



        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String penjudul = etjudul.getText().toString();
                String dess = etdes.getText().toString();
                String image = img.getText().toString();


//                updateInfo(id , penjudul , dess,image,date);
                alertDialog.dismiss();
            }
        });
    }

//    private boolean updateInfo(int id, String penjudul, String dess,String image,String date){
//
//
//        Toast.makeText(mContext, "Behasil Update Data", Toast.LENGTH_SHORT).show();
//
//        return true;
//    }


}
