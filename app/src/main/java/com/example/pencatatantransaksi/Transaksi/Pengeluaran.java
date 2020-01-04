package com.example.pencatatantransaksi.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.pencatatantransaksi.Helper.Format;
import com.example.pencatatantransaksi.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Pengeluaran extends AppCompatActivity {

    private TextView tvSaldo;
    private EditText txtDate, txtHarga, txtKeterangan;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;


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

    public void getDate(View view) {
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateFormat = new SimpleDateFormat(Format.DATE, Locale.US);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                txtDate.setText(dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
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
