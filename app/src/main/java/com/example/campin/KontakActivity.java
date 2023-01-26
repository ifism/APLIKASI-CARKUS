package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class KontakActivity extends AppCompatActivity {

    EditText ETnama, ETemail, ETsaran;
    ImageView ic_kembali;
    String nama,email,saran;
    Button BTNkirim;
    DrawerLayout drawerLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        ic_kembali   = findViewById(R.id.ic_kembali_kontakprofil);
        ETnama       = findViewById(R.id.ETkontak_nama);
        ETemail        = findViewById(R.id.ETemail_saran);
        ETsaran     = findViewById(R.id.ETkontak_saran);
        BTNkirim     = findViewById(R.id.BTNkontak_kirim);
        drawerLayout = findViewById(R.id.drawer_layout);

        // Progres Dialog
        progressDialog = new ProgressDialog(this);

        ic_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KontakActivity.this,BerandaActivity.class);
                startActivity(intent);
            }
        });

        // Buat Akun
        BTNkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Mengirim Saran....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                nama    = ETnama.getText().toString();
                email   = ETemail.getText().toString();
                saran   = ETsaran.getText().toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                },1000);
            }
        });

    }


    void validasiData(){

        if(nama.equals("")){
            progressDialog.dismiss();
            Toast.makeText(KontakActivity.this, "Periksa kembali saran yang anda masukkan!", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }

    }

    void kirimData(){
        AndroidNetworking.post("https://tekajeapunya.com/kelompok_11/carkus/addsaran.php")
                .addBodyParameter("nama",""+nama)
                .addBodyParameter("email",""+email)
                .addBodyParameter("saran",""+saran)
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
                            Toast.makeText(KontakActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(KontakActivity.this)
                                        .setMessage("Berhasil mengirim saran!")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = new Intent(KontakActivity.this, BerandaActivity.class);
                                                startActivity(i);
                                                KontakActivity.this.finish();
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(KontakActivity.this)
                                        .setMessage("Gagal mengirim saran!")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = getIntent();
                                                setResult(RESULT_CANCELED, i);
                                                KontakActivity.this.finish();
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

    public void ClickNavMenu (View view) {

        BerandaActivity.openDrawer(drawerLayout);

    }
    public void Clickberanda (View view) {

        BerandaActivity.redirectActivity(this, BerandaActivity.class);

    }

    public void Clickprofil (View view) {

        BerandaActivity.redirectActivity(this, ProfilActivity.class);

    }

    public void Clickriwayat (View view) {

        BerandaActivity.redirectActivity(this, RiwayatActivity.class);

    }

//    public void Clickkontak (View view) {
//
//        recreate();
//
//    }

    public void Clickinfo (View view) {

        BerandaActivity.redirectActivity(this, InfoActivity.class);

    }

    public void Clickkeluar(View view) {

        keluar(this);

    }

    public static void keluar(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Keluar");
        builder.setMessage("Apakah anda mau keluar dari aplikasi?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();

                System.exit(0);

            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.show();

    }

}