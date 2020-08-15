package com.example.mixis;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mixis.adapter.artikel.AdapterArtikel;
import com.example.mixis.adapter.artikel.EntitiyArtikel;
import com.example.mixis.api.BaseApiService;
import com.example.mixis.api.UtilsApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Artikel extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<EntitiyArtikel> artikelList;
    public AdapterArtikel adapterArtikel;

    Context mContext;

    BaseApiService mApiService;

    String resultToken, resultHeader;

    private String token = null;
    public static String Id;

    FloatingActionButton fab;

    SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Artikel");





        mContext = this;
        mApiService = UtilsApi.getAPIService();

        this.token = Login.token;



        mRecyclerView = (RecyclerView)findViewById(R.id.recylerartikel);


        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        artikelList = new ArrayList<>();

        Requestdata();

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipeartikel);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                artikelList.clear();
                mRecyclerView.setAdapter(null);
                Requestdata();
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogadd();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    public void Requestdata(){
        this.token = Login.token;


        mApiService.ArtikelRequest(token).enqueue(new Callback<ResponseBody>() {
            String id;
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        artikelList.clear();
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());

                        JSONArray dataArray = jsonRESULTS.getJSONArray("value");
                        for (int i = 0; i < dataArray.length(); i++) {
                            EntitiyArtikel artikel = new EntitiyArtikel();
                            JSONObject data = dataArray.getJSONObject(i);
                            id = data.getString("id");
                            Artikel.Id = this.id;
                            artikel.setId(data.getString("id"));
                            artikel.setTitle(data.getString("title"));
                            artikel.setDescription(data.getString("description"));
                            artikel.setDate(data.getString("created_at"));
//                            artikel.setImage(data.getInt("image"));
                            artikelList.add(artikel);
                            adapterArtikel = new AdapterArtikel(artikelList,mContext);
                            mRecyclerView.setAdapter(adapterArtikel);
                            adapterArtikel.notifyDataSetChanged();
                        }

                        swipe.setRefreshing(false);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(mContext, "data tidak ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    public void showdialogadd(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_artikel, null);
        dialogBuilder.setView(dialogView);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ' ' HH:mm:ss");
        final String date = sdf.format(new Date());

        final EditText judul = (EditText)dialogView.findViewById(R.id.artikeljudul);
        final EditText deksc = (EditText)dialogView.findViewById(R.id.artikeldeskripsi);
        final EditText imm = (EditText)dialogView.findViewById(R.id.artikelimage);


        Button tambahInfo = (Button) dialogView.findViewById(R.id.btnartikelpost);

        //set judul alert dialog agar tidak bingung

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();



        //membuat tombol addMurid bekerja dengan semestinya
        tambahInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                resultToken = "Bearer "+ extras.getString("Token");
                resultHeader = "application/json";


                String judd = judul.getText().toString();
                String dess = deksc.getText().toString();
                String imms = imm.getText().toString();

                mApiService.AddArtikelRequest(token,resultHeader,judd,dess,imms).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.isSuccessful()){
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                Log.d("Session ", "");
                                adapterArtikel.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();

                            } else {
                                Log.d("Session ", "");
                                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });



                alertDialog.dismiss();
            }
        });
    }


}
