package com.example.pencatatantransaksi.Transaksi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.pencatatantransaksi.Contoller.API;
import com.example.pencatatantransaksi.Helper.CariPelanggan.CariPelangganActivity;
import com.example.pencatatantransaksi.Helper.CariPelanggan.PelangganModel;
import com.example.pencatatantransaksi.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Pemasukan extends AppCompatActivity {

    private EditText txtPelanggan, txtDate, txtKeterangan;
    private TextView tvJumlah;
    String nama, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);
        txtPelanggan = findViewById(R.id.txtPelanggan);
        txtDate = findViewById(R.id.etTglTransaksi);
        tvJumlah = findViewById(R.id.etJmlBarang);
        txtKeterangan = findViewById(R.id.etKetTransaksi);
    }

    public void finish(View view) {
        finish();
    }

    public void getPelanggan(View view) {
        startActivityForResult(new Intent(this, CariPelangganActivity.class), CariPelangganActivity.KEY_DATA_PELANGGAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CariPelangganActivity.KEY_DATA_PELANGGAN){
            if (data != null) {
                String rest = Objects.requireNonNull(data.getStringExtra(CariPelangganActivity.GSON_DATA_PELANGGAN)).toString();
                PelangganModel pm = new Gson().fromJson(rest, PelangganModel.class);
                nama = pm.getPlgNama();
                txtPelanggan.setText(nama);
                getPemasukan();
            }
        }
    }

    public void getDate(View view) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                tanggal = dateFormat.format(newDate.getTime());
                txtDate.setText(tanggal);
                getPemasukan();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void getPemasukan(){
        if (nama != null && tanggal != null){
            final ProgressDialog progressDialog = ProgressDialog.show(this, "Harap Tunggu", "memuat data");
            AndroidNetworking.get(API.URL_LIHAT_PEMASUKAN)
                    .addQueryParameter("nama_pelanggan", nama)
                    .addQueryParameter("tgl_barang_keluar", tanggal)
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("P101", response);
                            progressDialog.cancel();
                            if (response.trim().equals("null")){
                                Toast.makeText(Pemasukan.this, "Tidak ada pemasukan", Toast.LENGTH_SHORT).show();
                            }else {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.trim());
                                    tvJumlah.setText(jsonObject.getString("harga_total"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.e("P117", anError.getErrorBody());
                            Toast.makeText(Pemasukan.this, "error", Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                        }
                    });
        }
    }


    public void simpan(View view){
        String plg = txtPelanggan.getText().toString();
        String tgl = txtDate.getText().toString();
        String jml = tvJumlah.getText().toString();
        String ket = txtKeterangan.getText().toString();
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Harap Tunggu", null);
        AndroidNetworking.post(API.URL_TAMBAH_PEMASUKAN)
                .addBodyParameter("nama_pelanggan", plg)
                .addBodyParameter("tanggal_transaksi", tgl)
                .addBodyParameter("jumlah", jml)
                .addBodyParameter("keterangan", ket)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Pemasukan.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Pemasukan.this, "error", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                });
    }
}
