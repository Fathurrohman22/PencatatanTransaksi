package com.example.pencatatantransaksi.Master;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerSatuan;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Barang extends AppCompatActivity {

    private String satuan, kategori, varian, stok;

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getVarian() {
        return varian;
    }

    public void setVarian(String varian) {
        this.varian = varian;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    Button tambahbarang;
    ImageView silang;
    EditText varian1, stok1;
    Spinner spinnerKategori, spinnerSatuan;
    AdapterSpinnerSatuan adapterSatuan;
    AdapterSpinnerKategori adapterKategori;
    ArrayList<ModelSatuan> listSatuan = new ArrayList<ModelSatuan>();
    ArrayList<ModelKategori> listKategori = new ArrayList<ModelKategori>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        varian1 = findViewById(R.id.etNamaBarang);
        stok1 = findViewById(R.id.etStokBarang);
        silang = findViewById(R.id.btnClose);
        tambahbarang = findViewById(R.id.tambah_barang);
        spinnerKategori = findViewById(R.id.my_spinnerKategori);
        spinnerSatuan = findViewById(R.id.my_spinnerSatuan);
        spinnerSatuan.setPrompt("Pilih Satuan");
        spinnerKategori.setPrompt("Pilih Satuan");


        spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setKategori(listKategori.get(position).getNama_kategori());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterKategori = new AdapterSpinnerKategori(Barang.this, listKategori);
        spinnerKategori.setAdapter(adapterKategori);

        callDataKategori();

        spinnerSatuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSatuan(listSatuan.get(position).getNama_satuan());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterSatuan = new AdapterSpinnerSatuan(Barang.this, listSatuan);
        spinnerSatuan.setAdapter(adapterSatuan);

        callDataSatuan();

        tambahbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan(getKategori(),getSatuan(),varian1.getText().toString(),stok1.getText().toString());
            }
        });

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void callDataKategori() {
        listKategori.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.URL_TAMPIL_KATEGORI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);
                        try {
                            //converting the string to json array object
                            JSONObject obj = new JSONObject(response);

                            //traversing through all the object
                            JSONArray array = obj.getJSONArray("result");
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                ModelKategori modelKategori = new ModelKategori();
                                modelKategori.setId_kategori(product.getString("id_kategori"));
                                modelKategori.setNama_kategori(product.getString("nama_kategori"));
                                listKategori.add(modelKategori);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterKategori = new AdapterSpinnerKategori(Barang.this, listKategori);
                        spinnerKategori.setAdapter(adapterKategori);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void callDataSatuan() {
        listSatuan.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.URL_TAMPIL_SATUAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);
                        try {
                            //converting the string to json array object
                            JSONObject obj = new JSONObject(response);

                            //traversing through all the object
                            JSONArray array = obj.getJSONArray("result");
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                ModelSatuan modelSatuan = new ModelSatuan();
                                modelSatuan.setId_satuan(product.getString("id_satuan"));
                                modelSatuan.setNama_satuan(product.getString("nama_satuan"));
                                modelSatuan.setJumlah(product.getString("jumlah"));
                                listSatuan.add(modelSatuan);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterSatuan = new AdapterSpinnerSatuan(Barang.this, listSatuan);
                        spinnerSatuan.setAdapter(adapterSatuan);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void simpan(final String satuan, final String kategori, final String varian, final String stok) {
        listSatuan.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.URL_TAMBAH_BARANG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);
                        Intent i = new Intent(Barang.this, Stok.class);
                        startActivity(i);
//                        i.putExtra("nama_kategori", kategori);
//                        i.putExtra("nama_satuan", satuan);
//                        i.putExtra("varian", varian);
//                        i.putExtra("stok", stok);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama_kategori", kategori);
                params.put("nama_satuan", satuan);
                params.put("varian", varian);
                params.put("stok", stok);
                return params;

            }};

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
