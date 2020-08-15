package com.example.mixis;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class TukarPointDetail extends AppCompatActivity {

    TextView btn_point;

    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tukar_point_detail);

        TextView judul = (TextView)findViewById(R.id.tvjudul);
        TextView deskripsi = (TextView)findViewById(R.id.tvdeskripsi);
        TextView point = (TextView)findViewById(R.id.tvpoint);
        TextView tanggal = (TextView)findViewById(R.id.tvtanggal);

//        ini untuk mengambil data yg dikirim dari ADAPTER (ONBINDVIEWHOLDER)

        String juduls = getIntent().getStringExtra("judul");
        String deskripsis = getIntent().getStringExtra("deskripsi");
        String points = getIntent().getStringExtra("point");
        String tanggals = getIntent().getStringExtra("tanggal");

//        INI UNTUK menset atau menampilkan data di atas pada XML

        judul.setText(juduls);
        deskripsi.setText(deskripsis);
        point.setText(points);
        tanggal.setText(tanggals);

        btn_point = (TextView)findViewById(R.id.tvpoint);
        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TukarPointDetail.this, MainActivity.class);
                //menginisialiasasi intent
                PendingIntent pendingIntent = PendingIntent.getActivity(TukarPointDetail.this, 0, intent, 0);
                //untuk memanggil activity di Notification
        /*
Menmbangun atau mensetup Notification dengan NotificationCompat.Builder
 */

                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(TukarPointDetail.this)
                        .setSmallIcon(R.drawable.logomixi) //ikon notification
                        .setContentTitle("Tukar Point") //judul konten
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)//untuk menswipe atau menghapus notification
                        .setContentText("Penukaran berhasil, silakan tunggu"); //isi text

/*
Kemudian kita harus menambahkan Notification dengan menggunakan NotificationManager
 */

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(NOTIFICATION_ID, builder.build()
                );
            }
        });

    }
}
