package com.example.pencatatantransaksi.Helper.CariPelanggan;

import android.content.Intent;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.pencatatantransaksi.Contoller.API;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.pencatatantransaksi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CariPelangganActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CariPelangganAdapter cpa;
    private PelangganModel pm;
    private ArrayList<PelangganModel> dataset;

    public final static int KEY_DATA_PELANGGAN = 997;
    public final static String GSON_DATA_PELANGGAN = "gsondatpel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_pelanggan);
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager ly = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ly);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CariPelangganActivity.this, TambahPelangganActivity.class), TambahPelangganActivity.KEY);
            }
        });
        getPelanggan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==TambahPelangganActivity.KEY){
            getPelanggan();
        }
    }

    private void getPelanggan(){
        AndroidNetworking.get(API.URL_TAMPIL_PELANGGAN)
                .setPriority(Priority.LOW)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("null")){
                            Toast.makeText(CariPelangganActivity.this, "tidak ada data", Toast.LENGTH_SHORT).show();
                        }else {
                            dataset = new ArrayList<>();
                            try {
                                JSONObject jsonObject = new JSONObject(response.trim());
                                JSONArray jsonArray = jsonObject.getJSONArray("value");
                                for (int i=0; i<jsonArray.length(); i++){
                                    PelangganModel pelmod = new Gson().fromJson(jsonArray.get(i).toString(), PelangganModel.class);
                                    dataset.add(pelmod);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            cpa = new CariPelangganAdapter(CariPelangganActivity.this, dataset);
                            recyclerView.setAdapter(cpa);
                            cpa.setOnItemClickListener(new CariPelangganAdapter.ClickListener() {
                                @Override
                                public void onItemClick(int position, View view) {
                                    pm = dataset.get(position);
                                    Intent intent = new Intent();
                                    intent.putExtra(GSON_DATA_PELANGGAN, pm.toString());
                                    setResult(KEY_DATA_PELANGGAN, intent);
                                    finish();
                                }

                                @Override
                                public void onItemLongClick(int position, View view) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(CariPelangganActivity.this, "Terjadi kesalahan jaringan", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    public void back(View view) {
        finish();
    }
}
