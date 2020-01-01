package com.example.pencatatantransaksi.Master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pencatatantransaksi.Adapter.AdapterBarang;
import com.example.pencatatantransaksi.Adapter.AdapterHarga;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerSatuan;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Model.ModelBarang;
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

public class InputHarga extends AppCompatActivity {

    private RecyclerView recycleHargaBarang;
    private ArrayList<ModelInputHarga> ListHargaBarang = new ArrayList<>();
    private AdapterHarga adapterHarga;
    private ImageView silang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_harga);

        recycleHargaBarang = findViewById(R.id.recycleHargaBarang);
        silang = findViewById(R.id.btnClose);
        recycleHargaBarang.setLayoutManager(new LinearLayoutManager(this));
        tampilBarang();

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void tampilBarang() {
        ListHargaBarang.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.URL_TAMPIL_HARGA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response",response);
                        try {
                            //converting the string to json array object
                            JSONObject obj= new JSONObject(response);

                            //traversing through all the object
                            JSONArray array = obj.getJSONArray("result");
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                ModelInputHarga modelInputHarga = new ModelInputHarga();
                                modelInputHarga.setNama_kategori(product.getString("nama_kategori"));
                                modelInputHarga.setNama_satuan(product.getString("nama_satuan"));
                                modelInputHarga.setHarga1pcs(product.getString("harga1pcs"));
                                modelInputHarga.setHarga10pcs(product.getString("harga10pcs"));
                                modelInputHarga.setHarga50pcs(product.getString("harga50pcs"));
                                modelInputHarga.setHarga100pcs(product.getString("harga100pcs"));
                                ListHargaBarang.add(modelInputHarga);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterHarga = new AdapterHarga(InputHarga.this, ListHargaBarang);
                        recycleHargaBarang.setAdapter(adapterHarga);
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

