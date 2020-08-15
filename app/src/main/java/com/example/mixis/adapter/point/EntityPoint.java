package com.example.mixis.adapter.point;

public class EntityPoint {

    String judul;
    String deskripsi;
    String point;
    String date;

    public EntityPoint(){}

    public EntityPoint(String judul, String deskripsi, String point, String date) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.point = point;
        this.date = date;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
