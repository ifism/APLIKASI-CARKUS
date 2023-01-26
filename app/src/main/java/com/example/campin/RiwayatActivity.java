package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class RiwayatActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        drawerLayout = findViewById(R.id.drawer_layout);

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

        recreate();

    }

//    public void Clickkontak (View view) {
//
//        BerandaActivity.redirectActivity(this, KontakActivity.class);
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