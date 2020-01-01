package com.example.pencatatantransaksi.Master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pencatatantransaksi.Adapter.AdapterKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSatuan;
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

public class Satuan extends AppCompatActivity {

    private EditText namaSatuan, jumlah;
    private Button tambahSatuan;
    private ImageView silang;
    private RecyclerView listViewSatuan;
    private ArrayList<ModelSatuan> ListSatuan = new ArrayList<>();
    private AdapterSatuan adapterSatuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satuan);

        namaSatuan = findViewById(R.id.etSatuanBesar);
        jumlah = findViewById(R.id.etJumlah);
        silang = findViewById(R.id.btnClose);
        tambahSatuan = findViewById(R.id.tambah_satuan);
        listViewSatuan = findViewById(R.id.listKategori);
        listViewSatuan.setLayoutManager(new LinearLayoutManager(this));
        loadProduct();

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tambahSatuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanSatuan(namaSatuan.getText().toString(), jumlah.getText().toString());
            }
        });
    }

    private void simpanSatuan(final String nama_satuan, final String jumlah) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.URL_TAMBAH_SATUAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response",response);
                        Toast.makeText(Satuan.this, "Tambah Berhasil", Toast.LENGTH_SHORT).show();
                        loadProduct();
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
                params.put("nama_satuan", nama_satuan);
                params.put("jumlah", jumlah);
                return params;

            }

        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }



    private void loadProduct() {
        ListSatuan.clear();
        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.URL_TAMPIL_SATUAN,
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
                                ModelSatuan modelSatuan = new ModelSatuan();
                                modelSatuan.setId_satuan(product.getString("id_satuan"));
                                modelSatuan.setNama_satuan(product.getString("nama_satuan"));
                                modelSatuan.setJumlah(product.getString("jumlah"));
                                ListSatuan.add(modelSatuan);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterSatuan = new AdapterSatuan(Satuan.this, ListSatuan);
                        listViewSatuan.setAdapter(adapterSatuan);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
