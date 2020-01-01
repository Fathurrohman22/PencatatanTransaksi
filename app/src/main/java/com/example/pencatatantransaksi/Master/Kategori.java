package com.example.pencatatantransaksi.Master;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pencatatantransaksi.Adapter.AdapterKategori;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Contoller.AppController;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kategori extends AppCompatActivity {

    private EditText namaKategori;
    private Button tambahKategori;
    private ImageView silang;
    private RecyclerView listViewKategori;
    private ArrayList<ModelKategori> ListKategori = new ArrayList<>();
    private AdapterKategori adapterKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        namaKategori = findViewById(R.id.etNama);
        tambahKategori = findViewById(R.id.tambah_kategori);
        silang = findViewById(R.id.btnClose);
        listViewKategori = findViewById(R.id.listKategori);
        listViewKategori.setLayoutManager(new LinearLayoutManager(this));
        loadProducts();
        tambahKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanKategori(namaKategori.getText().toString());
            }
        });

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadProducts() {

        ListKategori.clear();
        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.URL_TAMPIL_KATEGORI,
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
                                ModelKategori modelKategori = new ModelKategori();
                                modelKategori.setId_kategori(product.getString("id_kategori"));
                                modelKategori.setNama_kategori(product.getString("nama_kategori"));
                                ListKategori.add(modelKategori);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterKategori = new AdapterKategori(Kategori.this, ListKategori);
                        listViewKategori.setAdapter(adapterKategori);
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

    private void simpanKategori(final String nama_kategori) {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.URL_TAMBAH_KATEGORI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response",response);
                        Toast.makeText(Kategori.this, "Tambah Berhasil", Toast.LENGTH_SHORT).show();
                        loadProducts();
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
                params.put("nama_kategori", nama_kategori);
                return params;

            }

        };

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

//    @Override
////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////        if (item.getItemId()==R.id.delete){
////
////        }
////    }
}
