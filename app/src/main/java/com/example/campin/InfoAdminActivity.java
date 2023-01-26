package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InfoAdminActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_admin);

        drawerLayout = findViewById(R.id.drawer_layout1);

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

        redirectActivity(this,BerandaAdminActivity.class);

    }

    public void Clickprofil(View view) {

        redirectActivity(this,ProfilAdminActivity.class);

    }

    public void Clickinfo(View view) {

        recreate();

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