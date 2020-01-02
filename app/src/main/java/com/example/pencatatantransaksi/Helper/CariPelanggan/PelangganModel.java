package com.example.pencatatantransaksi.Helper.CariPelanggan;

import com.google.gson.annotations.SerializedName;

public class PelangganModel{

	@SerializedName("plg_alamat")
	private String plgAlamat;

	@SerializedName("plg_telp")
	private String plgTelp;

	@SerializedName("plg_nama")
	private String plgNama;

	@SerializedName("plg_id")
	private String plgId;

	public void setPlgAlamat(String plgAlamat){
		this.plgAlamat = plgAlamat;
	}

	public String getPlgAlamat(){
		return plgAlamat;
	}

	public void setPlgTelp(String plgTelp){
		this.plgTelp = plgTelp;
	}

	public String getPlgTelp(){
		return plgTelp;
	}

	public void setPlgNama(String plgNama){
		this.plgNama = plgNama;
	}

	public String getPlgNama(){
		return plgNama;
	}

	public void setPlgId(String plgId){
		this.plgId = plgId;
	}

	public String getPlgId(){
		return plgId;
	}

	@Override
 	public String toString(){
		return 
			"{" +
			"plg_alamat = '" + plgAlamat + '\'' + 
			",plg_telp = '" + plgTelp + '\'' + 
			",plg_nama = '" + plgNama + '\'' + 
			",plg_id = '" + plgId + '\'' + 
			"}";
		}
}