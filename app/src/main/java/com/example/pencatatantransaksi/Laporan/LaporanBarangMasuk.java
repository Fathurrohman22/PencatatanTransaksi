package com.example.pencatatantransaksi.Laporan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pencatatantransaksi.R;

public class LaporanBarangMasuk extends AppCompatActivity {

    ImageView silang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_barang_masuk);

        silang = findViewById(R.id.btnClose);
        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
