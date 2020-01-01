package com.example.pencatatantransaksi.Master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pencatatantransaksi.Adapter.AdapterHarga;
import com.example.pencatatantransaksi.Adapter.AdapterKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerSatuan;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Model.ModelInputHarga;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Harga extends AppCompatActivity {

    private String satuan;

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

    private String kategori;

    private EditText harga1pcs, harga10pcs, harga50pcs, harga100pcs;
    private Button simpan;
    private RecyclerView listDaftarHarga;
    private Spinner spinnerKategori, spinnerSatuan;
    private AdapterSpinnerSatuan adapterSatuan;
    private AdapterSpinnerKategori adapterKategori;
    private AdapterHarga adapterHarga;
    private ArrayList<ModelSatuan> listSatuan = new ArrayList<ModelSatuan>();
    private ArrayList<ModelKategori> listKategori = new ArrayList<ModelKategori>();
    private ArrayList<ModelInputHarga> listHarga = new ArrayList<ModelInputHarga>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga);

        harga1pcs = findViewById(R.id.etInputHarga1pcs);
        harga10pcs = findViewById(R.id.etInputHarga10pcs);
        harga50pcs = findViewById(R.id.etInputHarga50pcs);
        harga100pcs = findViewById(R.id.etInputHarga100pcs);
        simpan = findViewById(R.id.tambah_harga);
        spinnerSatuan = findViewById(R.id.my_spinnerSatuan);
        spinnerKategori = findViewById(R.id.my_spinnerKategori);
//        listDaftarHarga = findViewById(R.id.listHarga);
//        listDaftarHarga.setLayoutManager(new LinearLayoutManager(this));

        spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setKategori(listKategori.get(position).getNama_kategori());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterKategori = new AdapterSpinnerKategori(Harga.this, listKategori);
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
        adapterSatuan = new AdapterSpinnerSatuan(Harga.this, listSatuan);
        spinnerSatuan.setAdapter(adapterSatuan);

        callDataSatuan();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Harga.this, getSatuan()+getKategori()+harga1pcs.getText().toString()+harga10pcs.getText().toString()+harga50pcs.getText().toString()+harga50pcs.getText().toString(), Toast.LENGTH_SHORT).show();
                simpan(getSatuan(),getKategori(),harga1pcs.getText().toString(),harga10pcs.getText().toString(),harga50pcs.getText().toString(),harga50pcs.getText().toString());
            }
        });
    }

    private void simpan(final String satuan, final String kategori, final String harga1pcs, final String harga10pcs, final String harga50pcs, final String harga100pcs) {
        listHarga.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.URL_TAMBAH_HARGA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response Harga", response);
                        Toast.makeText(Harga.this, getSatuan() + getKategori(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Harga.this, InputHarga.class);
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
                params.put("harga_1pcs", harga1pcs);
                params.put("harga_10pcs", harga10pcs);
                params.put("harga_50pcs", harga50pcs);
                params.put("harga_100pcs", harga100pcs);
                return params;

            }};

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
                        adapterSatuan = new AdapterSpinnerSatuan(Harga.this, listSatuan);
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
                        adapterKategori = new AdapterSpinnerKategori(Harga.this, listKategori);
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
}
