package com.example.mixis.adapter.agenda;

public class EntitiyAgenda {

    String Judul,Deskripsi,Tanggal;

    public EntitiyAgenda(){}

    public EntitiyAgenda(String judul, String deskripsi, String tanggal) {
        Judul = judul;
        Deskripsi = deskripsi;
        Tanggal = tanggal;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }
}
