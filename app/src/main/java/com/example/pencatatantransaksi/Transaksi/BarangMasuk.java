package com.example.pencatatantransaksi.Transaksi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pencatatantransaksi.Adapter.AdapterBarang;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerKategori;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerSatuan;
import com.example.pencatatantransaksi.Adapter.AdapterSpinnerVarian;
import com.example.pencatatantransaksi.Adapter.AdapterVarian;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelBarangMasuk;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangMasuk extends AppCompatActivity {

    private String satuan, kategori, varian;

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

    private EditText tanggalbrgmasuk, jumlah1, hargabeli1;
    private Button simpan;
//    private DatePickerDialog datePickerDialog;
//    private SimpleDateFormat dateFormat;
    private Spinner spinnerKategori, spinnerSatuan, spinnerVarian;
    private AdapterSpinnerSatuan adapterSatuan;
    private AdapterSpinnerKategori adapterKategori;
    private AdapterSpinnerVarian adapterVarian;
    private AdapterBarang adapterBarang;
    private ArrayList<ModelSatuan> listSatuan = new ArrayList<ModelSatuan>();
    private ArrayList<ModelKategori> listKategori = new ArrayList<ModelKategori>();
    private ArrayList<ModelBarang> listBarang = new ArrayList<ModelBarang>();
    private ArrayList<ModelBarangMasuk> listBarangMasuk = new ArrayList<ModelBarangMasuk>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_masuk);

        jumlah1 = findViewById(R.id.etJmlBarang);
        simpan = findViewById(R.id.tambah_barang);
        spinnerKategori = findViewById(R.id.my_spinnerKategori);
        spinnerSatuan = findViewById(R.id.my_spinnerSatuan);
        spinnerVarian = findViewById(R.id.my_spinnerBarang);
//        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//        tanggalbrgmasuk = findViewById(R.id.etTglBeli);
//        tanggalbrgmasuk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateDialog();
//            }
//        });

        spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setKategori(listKategori.get(position).getNama_kategori());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterKategori = new AdapterSpinnerKategori(BarangMasuk.this, listKategori);
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
        adapterSatuan = new AdapterSpinnerSatuan(BarangMasuk.this, listSatuan);
        spinnerSatuan.setAdapter(adapterSatuan);

        callDataSatuan();

        spinnerVarian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setVarian(listBarang.get(position).getVarian());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterVarian = new AdapterSpinnerVarian(BarangMasuk.this, listBarang);
        spinnerVarian.setAdapter(adapterVarian);

        callDataBarang();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan(getKategori(),getSatuan(),getVarian(),jumlah1.getText().toString());
            }
        });
    }

    private void simpan(final String satuan, final String kategori, final String varian, final String jumlah) {
        listBarangMasuk.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.URL_TAMBAH_BARANGMASUK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);
                        Toast.makeText(BarangMasuk.this, "Berhasil", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(Barang.this, Stok.class);
//                        startActivity(i);
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
                params.put("nama_varian", varian);
                params.put("jumlah", jumlah);
                return params;

            }};

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void callDataBarang() {
        listBarang.clear();

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
                                listBarang.add(modelBarang);
                            }

                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterVarian = new AdapterSpinnerVarian(BarangMasuk.this, listBarang);
                        spinnerVarian.setAdapter(adapterVarian);
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
                        adapterSatuan = new AdapterSpinnerSatuan(BarangMasuk.this, listSatuan);
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
                        adapterKategori = new AdapterSpinnerKategori(BarangMasuk.this, listKategori);
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

//    private void showDateDialog() {
//        /**
//         * Calendar untuk mendapatkan tanggal sekarang
//         */
//        Calendar newCalendar = Calendar.getInstance();
//
//        /**
//         * Initiate DatePicker dialog
//         */
//        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                /**
//                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
//                 */
//
//                /**
//                 * Set Calendar untuk menampung tanggal yang dipilih
//                 */
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//
//                /**
//                 * Update TextView dengan tanggal yang kita pilih
//                 */
//                tanggalbrgmasuk.setText(dateFormat.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//        /**
//         * Tampilkan DatePicker dialog
//         */
//        datePickerDialog.show();
//    }
}

