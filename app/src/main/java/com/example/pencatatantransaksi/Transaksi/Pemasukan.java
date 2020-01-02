package com.example.pencatatantransaksi.Transaksi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.pencatatantransaksi.Helper.CariPelanggan.CariPelangganActivity;
import com.example.pencatatantransaksi.Helper.CariPelanggan.PelangganModel;
import com.example.pencatatantransaksi.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Pemasukan extends AppCompatActivity {

    private EditText txtPelanggan, txtDate, txtJumlah, txtKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);
        txtPelanggan = findViewById(R.id.txtPelanggan);
        txtDate = findViewById(R.id.etTglTransaksi);
        txtJumlah = findViewById(R.id.etJmlBarang);
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
                txtPelanggan.setText(pm.getPlgNama());
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
                txtDate.setText(dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void simpan(View view){
        String plg = txtPelanggan.getText().toString();
        String tgl = txtDate.getText().toString();
        String jml = txtJumlah.getText().toString();
        String ket = txtKeterangan.getText().toString();

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Harap Tunggu", null);
        AndroidNetworking.post("")
                .addBodyParameter("", plg)
                .addBodyParameter("", tgl)
                .addBodyParameter("", jml)
                .addBodyParameter("", ket)
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
