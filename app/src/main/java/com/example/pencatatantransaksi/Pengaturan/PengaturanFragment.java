package com.example.pencatatantransaksi.Pengaturan;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pencatatantransaksi.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class PengaturanFragment extends Fragment {


    public PengaturanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaturan, container, false);
        view.findViewById(R.id.backup_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.reset_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Yakin?");
                builder.setMessage("Akan menghapus seluruh data.");
                builder.setIcon(R.drawable.ic_warning_black_24dp);
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "masih dalam pengembangan", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        view.findViewById(R.id.petunjuk_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "masih dalam pengembangan", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
