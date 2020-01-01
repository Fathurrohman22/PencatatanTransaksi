package com.example.pencatatantransaksi.Laporan;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pencatatantransaksi.R;
import com.example.pencatatantransaksi.Transaksi.BarangMasuk;


/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanFragment extends Fragment {

    CardView lapbarang, lapbarangkeluar, lapkeuangan, grafpenjualan;

    public LaporanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_laporan, container, false);

        lapbarang = v.findViewById(R.id.laporanBarang_card);
        lapbarangkeluar = v.findViewById(R.id.laporanBrgKeluar_card);
        lapkeuangan = v.findViewById(R.id.laporanKeuangan_card);
        grafpenjualan = v.findViewById(R.id.laporanGrafik_card);

        lapbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), LaporanBarang.class);
                startActivity(a);
            }
        });

        lapbarangkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), LaporanBarangKeluar.class);
                startActivity(a);
            }
        });

        lapkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), LaporanKeuangan.class);
                startActivity(a);
            }
        });

        grafpenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), GrafikPenjualan.class);
                startActivity(a);
            }
        });

        return  v;
    }

}
