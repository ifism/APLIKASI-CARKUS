package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class DaftarActivity extends AppCompatActivity {

    EditText ETDnama_lengkap, ETDnama_pengguna, ETDno_handphone, ETDemail, ETDkata_sandi;
    String nama_lengkap, nama_pengguna, nohp, email, kata_sandi;
    TextView TVDhello, TVDsudah_punyaakun, TVDmasuk;
    Button BTNDaftar;
    ProgressDialog progressDialog;
    float v=0;
    Bitmap bitMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);


        // Text View
        TVDhello = findViewById(R.id.TVDhello);
        TVDsudah_punyaakun = findViewById(R.id.TVDsudah_punyaakun);
        TVDmasuk = findViewById(R.id.TVDmasuk);
        // Edit Text
        ETDnama_lengkap = findViewById(R.id.ETDnama_lengkap);
        ETDnama_pengguna = findViewById(R.id.ETDnama_pengguna);
        ETDno_handphone = findViewById(R.id.ETDNo_Telepon);
        ETDemail = findViewById(R.id.ETDemail);
        ETDkata_sandi = findViewById(R.id.ETDkata_sandi);
        // Button
        BTNDaftar = findViewById(R.id.BTNDaftar);
        // Progres Dialog
        progressDialog = new ProgressDialog(this);


        // Text View
        TVDhello.setTranslationX(400);
        TVDsudah_punyaakun.setTranslationX(400);
        TVDmasuk.setTranslationX(400);
        // Edit Text
        ETDnama_lengkap.setTranslationX(400);
        ETDnama_pengguna.setTranslationX(400);
        ETDno_handphone.setTranslationX(400);
        ETDemail.setTranslationX(400);
        ETDkata_sandi.setTranslationX(400);
        // Button
        BTNDaftar.setTranslationX(400);


        // Text View
        TVDhello.setAlpha(v);
        TVDsudah_punyaakun.setAlpha(v);
        TVDmasuk.setAlpha(v);
        // Edit Text
        ETDnama_lengkap.setAlpha(v);
        ETDnama_pengguna.setAlpha(v);
        ETDno_handphone.setAlpha(v);
        ETDemail.setAlpha(v);
        ETDkata_sandi.setAlpha(v);
        // Button
        BTNDaftar.setAlpha(v);


        // Delay
        TVDhello.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(75).start();

        ETDnama_lengkap.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        ETDnama_pengguna.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(125).start();
        ETDno_handphone.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(175).start();
        ETDemail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        ETDkata_sandi.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(225).start();

        BTNDaftar.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(250).start();

        TVDsudah_punyaakun.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(275).start();
        TVDmasuk.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();


        // Buat Akun
        BTNDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Membuat Akun Baru....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                nama_lengkap    = ETDnama_lengkap.getText().toString();
                nama_pengguna   = ETDnama_pengguna.getText().toString();
                nohp            = ETDno_handphone.getText().toString();
                email           = ETDemail.getText().toString();
                kata_sandi      = ETDkata_sandi.getText().toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                },1000);
            }
        });


        TVDmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarActivity.this,BerandaActivity.class);
                startActivity(intent);
            }
        });
    }


    void validasiData(){

        if(nama_lengkap.equals("") || nama_pengguna.equals("") || nohp.equals("") || email.equals("") || kata_sandi.equals("")){
            progressDialog.dismiss();
            Toast.makeText(DaftarActivity.this, "Kolom tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }

    }


    void kirimData(){

        AndroidNetworking.post("https://tekajeapunya.com/kelompok_11/carkus/register.php")
                .addBodyParameter("nama_lengkap",""+nama_lengkap)
                .addBodyParameter("nama_pengguna",""+nama_pengguna)
                .addBodyParameter("nohp",""+nohp)
                .addBodyParameter("email",""+email)
                .addBodyParameter("kata_sandi",""+kata_sandi)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(DaftarActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(DaftarActivity.this)
                                        .setMessage("Berhasil Membuat Akun!")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = new Intent(DaftarActivity.this, LoginActivity.class);
                                                startActivity(i);
                                                DaftarActivity.this.finish();
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(DaftarActivity.this)
                                        .setMessage("Gagal Membuat Akun!")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = getIntent();
                                                setResult(RESULT_CANCELED, i);
                                                DaftarActivity.this.finish();
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });

    }


}