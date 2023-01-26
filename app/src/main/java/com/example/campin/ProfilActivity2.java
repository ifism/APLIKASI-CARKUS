package com.example.campin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class ProfilActivity2 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button BTNupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil2);

        drawerLayout = findViewById(R.id.drawer_layout);
        BTNupdate    = findViewById(R.id.BTNUp_profil);

        BTNupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity2.this, UpdateProfilActivity2.class);
                startActivity(intent);
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

        recreate();
    }

    public void Clickriwayat (View view) {

        BerandaActivity.redirectActivity(this,RiwayatActivity.class);

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