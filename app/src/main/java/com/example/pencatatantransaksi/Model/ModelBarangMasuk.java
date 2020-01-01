package com.example.pencatatantransaksi.Model;

public class ModelBarangMasuk {
    private String nama_kategori;
    private String nama_satuan;
    private String nama_varian;
    private String jumlah;

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public String getNama_satuan() {
        return nama_satuan;
    }

    public void setNama_satuan(String nama_satuan) {
        this.nama_satuan = nama_satuan;
    }

    public String getNama_varian() {
        return nama_varian;
    }

    public void setNama_varian(String nama_varian) {
        this.nama_varian = nama_varian;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(String harga_beli) {
        this.harga_beli = harga_beli;
    }

    private String harga_beli;
}
