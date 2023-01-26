package com.example.campin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailPaketPilihanActivity extends AppCompatActivity {

    EditText ETnama_paket,ETjumlah_pertemuan,ETharga, ETmentor;
    String id,nama_paket,jumlah_pertemuan,harga,mentor,foto;
    CircleImageView image;
    ProgressDialog progressDialog;
    ImageButton BTNImage;
    Button BTNpilih;

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
        setContentView(R.layout.activity_detail_paket_pilihan);

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
        nama_paket  = getIntent().getStringExtra("nama_paket");
        jumlah_pertemuan   = getIntent().getStringExtra("jumlah_pertemuan");
        harga  = getIntent().getStringExtra("harga");
        mentor  = getIntent().getStringExtra("mentor");
        foto        = getIntent().getStringExtra("foto");


        ETnama_paket      = findViewById(R.id.ETUp_namapaket);
        ETjumlah_pertemuan = findViewById(R.id.ETUp_jumlahpertemuan);
        ETharga            = findViewById(R.id.ETUp_harga);
        ETmentor            = findViewById(R.id.ETUp_mentor);
        image             = findViewById(R.id.pic_paket);
        BTNImage          = findViewById(R.id.IM_ubahpaket);
        BTNpilih        = findViewById(R.id.BTNpilih);

        ETnama_paket.setText(nama_paket);
        ETjumlah_pertemuan.setText(jumlah_pertemuan);
        ETharga.setText(harga);
        ETmentor.setText(mentor);


        if (foto.equals("")) {
            Picasso.get().load("https://tekajeapunya.com/kelompok_11/image/noimages.png").into(image);
        } else {
            Picasso.get().load("https://tekajeapunya.com/kelompok_11/image/" + foto).into(image);
        }

        progressDialog = new ProgressDialog(this);



        BTNpilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPaketPilihanActivity.this,DaftarPaketActivity.class);
                startActivity(intent);
            }
        });


    }

}