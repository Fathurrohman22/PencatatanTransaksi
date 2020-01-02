package com.example.pencatatantransaksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pencatatantransaksi.Laporan.LaporanFragment;
import com.example.pencatatantransaksi.Master.MasterFragment;
import com.example.pencatatantransaksi.Pengaturan.PengaturanFragment;
import com.example.pencatatantransaksi.Transaksi.TransaksiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        loadFragment(new MasterFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        welcome();
    }

    private void welcome(){
        Date dt = new Date();
        int h = dt.getHours();
        int m = dt.getMinutes();
        if (h>=1 && h<=10){
            Objects.requireNonNull(getSupportActionBar()).setSubtitle("Selamat pagi");
        }else if (h>=10 && h<=14){
            Objects.requireNonNull(getSupportActionBar()).setSubtitle("Selamat siang");
        }else if (h>=14 && h<=20){
            Objects.requireNonNull(getSupportActionBar()).setSubtitle("Selamat sore");
        }else if (h>=20 && h<=24) {
            Objects.requireNonNull(getSupportActionBar()).setSubtitle("Selamat malam");
        }


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
