package com.example.pencatatantransaksi.Master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pencatatantransaksi.Adapter.AdapterBarang;
import com.example.pencatatantransaksi.Adapter.AdapterKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerSatuan;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Stok extends AppCompatActivity {

    private RecyclerView recycleBarang;
    private ArrayList<ModelBarang> ListBarang = new ArrayList<>();
    private AdapterBarang adapterBarang;
    private ImageView silang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok);

        recycleBarang = findViewById(R.id.recycleBarang);
        silang = findViewById(R.id.btnClose);
        recycleBarang.setLayoutManager(new LinearLayoutManager(this));
        tampilBarang();

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void tampilBarang() {
        ListBarang.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.URL_TAMPIL_BARANG,
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
                                ModelBarang modelBarang = new ModelBarang();
                                modelBarang.setNama_kategori(product.getString("nama_kategori"));
                                modelBarang.setNama_satuan(product.getString("nama_satuan"));
                                modelBarang.setVarian(product.getString("varian"));
                                modelBarang.setStok(product.getString("stok"));
                                ListBarang.add(modelBarang);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterBarang = new AdapterBarang(Stok.this, ListBarang);
                        recycleBarang.setAdapter(adapterBarang);
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
