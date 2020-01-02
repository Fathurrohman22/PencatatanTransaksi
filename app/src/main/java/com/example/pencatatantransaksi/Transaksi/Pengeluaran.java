package com.example.pencatatantransaksi.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.pencatatantransaksi.R;

import java.util.Objects;

public class Pengeluaran extends AppCompatActivity {

    private TextView tvSaldo;
    private EditText txtDate, txtHarga, txtKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);
        tvSaldo = findViewById(R.id.tvSaldo);
        txtDate = findViewById(R.id.etTglTransaksi);
        txtHarga = findViewById(R.id.etHargaKeluar);
        txtKeterangan = findViewById(R.id.etKetTransaksi);
        getSaldo();
    }

    private void getSaldo() {
        AndroidNetworking.get("")
                .addQueryParameter("", "")
                .setPriority(Priority.LOW)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        setTvSaldo(Integer.parseInt(response));
                    }

                    @Override
                    public void onError(ANError anError) {
                        tvSaldo.setText("-");
                        setTvSaldo(0);
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setTvSaldo(int nominal){
        tvSaldo.setText("Rp. "+nominal);
    }

    public void Simpan(View view) {
        String tgl = txtDate.getText().toString();
        String hrg = txtHarga.getText().toString();
        String ket = txtKeterangan.getText().toString();

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Harap Tunggu", "menyimpan data");
        AndroidNetworking.post("")
                .addBodyParameter("", tgl)
                .addBodyParameter("", hrg)
                .addBodyParameter("", ket)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Pengeluaran.this, "response", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Pengeluaran.this, "error", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                });
    }
}
