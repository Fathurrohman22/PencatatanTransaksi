package com.example.pencatatantransaksi.Helper.CariPelanggan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.pencatatantransaksi.R;

import java.util.Objects;

public class TambahPelangganActivity extends AppCompatActivity {

    private EditText txtNama, txtTelp, txtAlamat;
    public static final int KEY = 273;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pelanggan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Pelanggan");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtNama = findViewById(R.id.txtNama);
        txtTelp = findViewById(R.id.txtTelp);
        txtAlamat = findViewById(R.id.txtAlamat);
    }

    public void Simpan(View view){
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Harap Tunggu", "sedang menyimpan data");
        AndroidNetworking.post("")
                .addBodyParameter("nama",txtNama.getText().toString())
                .addBodyParameter("alamat",txtAlamat.getText().toString())
                .addBodyParameter("telp",txtTelp.getText().toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.cancel();
                        setResult(KEY);
                        finish();
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.cancel();
                    }
                });
    }
}
