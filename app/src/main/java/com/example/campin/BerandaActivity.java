package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class BerandaActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RelativeLayout fitur_kirimSaran, fitur_kontakpengajar, fitur_pilihpaket, fitur_pesanan;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        drawerLayout        = findViewById(R.id.drawer_layout);
        fitur_pilihpaket   = findViewById(R.id.fitur1_listbarang);
        fitur_pesanan   = findViewById(R.id.fitur2_pesanan);
        fitur_kontakpengajar   = findViewById(R.id.fitur3_kontakstaff);
        fitur_kirimSaran    = findViewById(R.id.fitur4_kirimsaran);


        fitur_pilihpaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this, PaketUserActivity.class);
                startActivity(intent);
            }
        });

        fitur_pesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this,List_Pendaftar_Activity.class);
                startActivity(intent);
            }
        });

        fitur_kirimSaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this,KontakActivity.class);
                startActivity(intent);
            }
        });

        fitur_kontakpengajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this,KontakMentorActivity.class);
                startActivity(intent);
            }
        });

    }


    public void ClickNavMenu (View view) {

        openDrawer(drawerLayout);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer((GravityCompat.START));

    }

    private static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }

    public void Clickberanda(View view) {

        recreate();

    }

    public void Clickprofil(View view) {

        redirectActivity(this,ProfilActivity.class);

    }

    public void Clickriwayat(View view) {

        redirectActivity(this,RiwayatActivity.class);

    }

    public void Clickinfo(View view) {

        redirectActivity(this,InfoActivity.class);

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

    protected void onPause() {
        super.onPause();

        closeDrawer(drawerLayout);

    }

    public static void redirectActivity(Activity activity, Class aClass) {

        Intent intent = new Intent(activity,aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(intent);

    }

}