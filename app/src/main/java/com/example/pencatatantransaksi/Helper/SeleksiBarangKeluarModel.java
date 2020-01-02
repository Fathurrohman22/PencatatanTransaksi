package com.example.pencatatantransaksi.Helper;

public class SeleksiBarangKeluarModel {
    private int posisi;
    private int unit;

    public SeleksiBarangKeluarModel(int posisi, int unit) {
        this.posisi = posisi;
        this.unit = unit;
    }

    public int getPosisi() {
        return posisi;
    }

    public void setPosisi(int posisi) {
        this.posisi = posisi;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
