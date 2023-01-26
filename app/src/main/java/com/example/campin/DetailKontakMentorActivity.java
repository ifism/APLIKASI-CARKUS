package com.example.campin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKontakMentorActivity extends AppCompatActivity {

    EditText ETnama_staff,ETid_staff,ETno_hp;
    String id,nama_staff,id_staff,no_hp,foto;
    CircleImageView image;
    ProgressDialog progressDialog;
    Button BTNkonfirmasi;

    String pilihan;
    private static final int PHOTO_REQUEST_GALLERY = 1;//gallery
    static final int REQUEST_TAKE_PHOTO = 1;
    final int CODE_GALLERY_REQUEST = 999;
    String rPilihan[]= {"Memotret foto", "Dari album"};
    public final String APP_TAG = "MyApp";
    public String photoFileName = "photo.jpg";
    File photoFile;

    Bitmap bitMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kontakmentor_pilihan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_kembali);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Intent, get data from main
        id          = getIntent().getStringExtra("id");
        nama_staff  = getIntent().getStringExtra("nama_staff");
        id_staff   = getIntent().getStringExtra("id_staff");
        no_hp  = getIntent().getStringExtra("no_hp");
        foto        = getIntent().getStringExtra("foto");


        ETnama_staff      = findViewById(R.id.ETUp_namamentor);
        ETid_staff = findViewById(R.id.ETUp_idmentor);
        ETno_hp            = findViewById(R.id.ETUp_nohp);
        image             = findViewById(R.id.pic_mentor);
        BTNkonfirmasi       = findViewById(R.id.BTNkonfirmasi);

        ETnama_staff.setText(nama_staff);
        ETid_staff.setText(id_staff);
        ETno_hp.setText(no_hp);



        if (foto.equals("")) {
            Picasso.get().load("https://tekajeapunya.com/kelompok_11/image/noimages.png").into(image);
        } else {
            Picasso.get().load("https://tekajeapunya.com/kelompok_11/image/" + foto).into(image);
        }

        progressDialog = new ProgressDialog(this);



        BTNkonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailKontakMentorActivity.this,whatsapp.class);
                startActivity(intent);
            }
        });


    }

}