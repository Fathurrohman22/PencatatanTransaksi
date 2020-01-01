package com.example.pencatatantransaksi.Transaksi;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pencatatantransaksi.Master.InputHarga;
import com.example.pencatatantransaksi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransaksiFragment extends Fragment {

    CardView barangmasuk, barangkeluar, pemasukan, pengeluaran, input_harga;


    public TransaksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaksi, container, false);

        barangmasuk = v.findViewById(R.id.transaksiBrgMasuk_card);
        barangkeluar = v.findViewById(R.id.transaksiBrgKeluar_card);
        pemasukan = v.findViewById(R.id.transaksiPemasukan_card);
        pengeluaran = v.findViewById(R.id.transaksiPengeluaran_card);
        input_harga = v.findViewById(R.id.transaksiHarga_card);

        barangmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), BarangMasuk.class);
                startActivity(a);
            }
        });

        barangkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), BarangKeluar.class);
                startActivity(a);
            }
        });

        pemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), Pemasukan.class);
                startActivity(a);
            }
        });

        pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), Pengeluaran.class);
                startActivity(a);
            }
        });

//        input_harga.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(getActivity(), InputHarga.class);
//                startActivity(a);
//            }
//        });

        return v;
    }

}
