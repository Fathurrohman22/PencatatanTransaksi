package com.example.pencatatantransaksi.Transaksi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.pencatatantransaksi.Adapter.AdapterBarang;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerSatuan;
import com.example.pencatatantransaksi.Adapter.AdapterVarian;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Helper.CariPelanggan.CariPelangganActivity;
import com.example.pencatatantransaksi.Helper.CariPelanggan.PelangganModel;
import com.example.pencatatantransaksi.Master.Barang;
import com.example.pencatatantransaksi.Master.Stok;
import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelBarangKeluar;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class BarangKeluar extends AppCompatActivity {

    String nama_pelanggan;
    String satuan;
    String kategori;

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

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

    String varian;

    private EditText namapelanggan, tanggalbrgkeluar;
    private TextView tvTotal;
    private RecyclerView rvVarian;
    private Button simpan;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;
    private Spinner spinnerKategori, spinnerSatuan;
    private AdapterVarian adapterVarian;
    private AdapterSpinnerKategori adapterKategori;
    private AdapterSpinnerSatuan adapterSatuan;
    private ArrayList<ModelSatuan> listSatuan = new ArrayList<ModelSatuan>();
    private ArrayList<ModelKategori> listKategori = new ArrayList<ModelKategori>();
    private ArrayList<ModelBarang> listBarang = new ArrayList<>();
    private ArrayList<ModelBarangKeluar> listBarangKeluar = new ArrayList<ModelBarangKeluar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_keluar);

        tvTotal = findViewById(R.id.tvTotal);
        namapelanggan = findViewById(R.id.etNamaPel);
        rvVarian = findViewById(R.id.rvVarian);
        rvVarian.setLayoutManager(new LinearLayoutManager(this));
        tampilVarian();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tanggalbrgkeluar = findViewById(R.id.etTglJual);
        simpan = findViewById(R.id.tambah_barang);
        spinnerKategori = findViewById(R.id.my_spinnerKategori);
        spinnerSatuan = findViewById(R.id.my_spinnerSatuan);

        namapelanggan.setFocusable(false);
        namapelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(BarangKeluar.this, CariPelangganActivity.class), CariPelangganActivity.KEY_DATA_PELANGGAN);
            }
        });


        spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setKategori(listKategori.get(position).getNama_kategori());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterKategori = new AdapterSpinnerKategori(BarangKeluar.this, listKategori);
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
        adapterSatuan = new AdapterSpinnerSatuan(BarangKeluar.this, listSatuan);
        spinnerSatuan.setAdapter(adapterSatuan);

        callDataSatuan();

        tanggalbrgkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });


//        tanggalbrgkeluar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                showDateDialog();
//                return false;
//            }
//        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanBarangKeluar(getKategori(), getSatuan(), getVarian(), namapelanggan.getText().toString(), tvTotal.getText().toString(), tanggalbrgkeluar.getText().toString());
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CariPelangganActivity.KEY_DATA_PELANGGAN){
            if (data != null) {
                String rest = Objects.requireNonNull(data.getStringExtra(CariPelangganActivity.GSON_DATA_PELANGGAN)).toString();
                PelangganModel pm = new Gson().fromJson(rest, PelangganModel.class);
                namapelanggan.setText(pm.getPlgNama());
            }
        }
    }

    private void tampilVarian() {
        listBarangKeluar.clear();

        AndroidNetworking.get(API.URL_TAMPIL_BARANG)
                .setPriority(Priority.LOW)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response Barang Keluar", response);
                        try {
                            //converting the string to json array object
                            JSONObject obj = new JSONObject(response);

                            //traversing through all the object
                            JSONArray array = obj.getJSONArray("result");
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                ModelBarang modelBarang = new ModelBarang();
                                modelBarang.setVarian(product.getString("varian"));
                                listBarang.add(modelBarang);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapterVarian = new AdapterVarian(BarangKeluar.this, listBarang, tvTotal);
                        adapterVarian.notifyDataSetChanged();
                        rvVarian.setAdapter(adapterVarian);
                        adapterVarian.setOnItemClickListener(new AdapterVarian.ClickListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onItemClick(int position, View view) {

                            }

                            @Override
                            public void onItemLongClick(int position, View view) {

                            }
                        });
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tanggalbrgkeluar.setText(dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
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
                        adapterSatuan = new AdapterSpinnerSatuan(BarangKeluar.this, listSatuan);
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
                        adapterKategori = new AdapterSpinnerKategori(BarangKeluar.this, listKategori);
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

    private void simpanBarangKeluar(final String nama_pelanggan, final String satuan, final String kategori, final String varian, final String jumlah, final String tanggalbrgkeluar) {
        listBarangKeluar.clear();

        Map<String, String> params = new HashMap<String, String>();
        params.put("nama_pelanggan", nama_pelanggan);
        params.put("tgl_barang_keluar", tanggalbrgkeluar);
        params.put("nama_kategori", kategori);
        params.put("nama_satuan", satuan);
        params.put("nama_varian", varian);
        params.put("jumlah", jumlah);

        AndroidNetworking.post(API.URL_TAMBAH_BARANGKELUAR)
                .addBodyParameter(params)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);
                        Toast.makeText(BarangKeluar.this, response, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(BarangKeluar.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
