package com.example.pencatatantransaksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pencatatantransaksi.Laporan.LaporanFragment;
import com.example.pencatatantransaksi.Master.MasterFragment;
import com.example.pencatatantransaksi.Pengaturan.PengaturanFragment;
import com.example.pencatatantransaksi.Transaksi.TransaksiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        loadFragment(new MasterFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.bottomNavigationMaster:
                fragment = new MasterFragment();
                break;
            case R.id.bottomNavigationTransaksi:
                fragment = new TransaksiFragment();
                break;
            case R.id.bottomNavigationLaporan:
                fragment = new LaporanFragment();
                break;
            case R.id.bottomNavigationPengaturan:
                fragment = new PengaturanFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
