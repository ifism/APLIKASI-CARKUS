package com.example.campin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateProfilActivity2 extends AppCompatActivity {

    ImageView icKembaliUpProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil2);

        icKembaliUpProfil = findViewById(R.id.ic_kembali_ubahprofil);

        icKembaliUpProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfilActivity2.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

    }
}