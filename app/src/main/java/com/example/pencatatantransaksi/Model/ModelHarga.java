package com.example.pencatatantransaksi.Model;

import com.google.gson.annotations.SerializedName;

public class ModelHarga{

	@SerializedName("harga_100pcs")
	private String harga100pcs;

	@SerializedName("harga_50pcs")
	private String harga50pcs;

	@SerializedName("harga_10pcs")
	private String harga10pcs;

	@SerializedName("nama_satuan")
	private String namaSatuan;

	@SerializedName("nama_kategori")
	private String namaKategori;

	@SerializedName("harga_1pcs")
	private String harga1pcs;

	public void setHarga100pcs(String harga100pcs){
		this.harga100pcs = harga100pcs;
	}

	public String getHarga100pcs(){
		return harga100pcs;
	}

	public void setHarga50pcs(String harga50pcs){
		this.harga50pcs = harga50pcs;
	}

	public String getHarga50pcs(){
		return harga50pcs;
	}

	public void setHarga10pcs(String harga10pcs){
		this.harga10pcs = harga10pcs;
	}

	public String getHarga10pcs(){
		return harga10pcs;
	}

	public void setNamaSatuan(String namaSatuan){
		this.namaSatuan = namaSatuan;
	}

	public String getNamaSatuan(){
		return namaSatuan;
	}

	public void setNamaKategori(String namaKategori){
		this.namaKategori = namaKategori;
	}

	public String getNamaKategori(){
		return namaKategori;
	}

	public void setHarga1pcs(String harga1pcs){
		this.harga1pcs = harga1pcs;
	}

	public String getHarga1pcs(){
		return harga1pcs;
	}

	@Override
 	public String toString(){
		return 
			"{" +
			"harga_100pcs = '" + harga100pcs + '\'' + 
			",harga_50pcs = '" + harga50pcs + '\'' + 
			",harga_10pcs = '" + harga10pcs + '\'' + 
			",nama_satuan = '" + namaSatuan + '\'' + 
			",nama_kategori = '" + namaKategori + '\'' + 
			",harga_1pcs = '" + harga1pcs + '\'' + 
			"}";
		}
}