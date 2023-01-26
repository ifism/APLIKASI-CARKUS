package com.example.campin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class List_Pendaftar_Activity extends AppCompatActivity {

    ImageView ic_kembali;
    SwipeRefreshLayout srl_main;
    ArrayList<String> array_nama_paket,array_mentor,array_nama_pendaftar,array_NIK,array_no_telp, array_foto,array_id;
    ProgressDialog progressDialog;
    ListView listProses;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pendaftar);

        //set variable sesuai dengan widget yang digunakan
        listProses = findViewById(R.id.LV);
        srl_main    = findViewById(R.id.swipe_container);
        progressDialog = new ProgressDialog(this);

        ic_kembali = findViewById(R.id.ic_kembali_kontak);

        ic_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_Pendaftar_Activity.this,BerandaActivity.class);
                startActivity(intent);
            }
        });

        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });
        // Scheme colors for animation
        srl_main.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );

        scrollRefresh();

    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data User....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },300);
    }

    void initializeArray(){
        array_nama_paket    = new ArrayList<String>();
        array_mentor    = new ArrayList<String>();
        array_nama_pendaftar      = new ArrayList<String>();
        array_NIK          = new ArrayList<String>();
        array_no_telp          = new ArrayList<String>();
        array_foto          = new ArrayList<String>();
        array_id            = new ArrayList<String>();

        array_nama_paket.clear();
        array_mentor.clear();
        array_nama_pendaftar.clear();
        array_NIK.clear();
        array_no_telp.clear();
        array_foto.clear();
        array_id.clear();

    }

    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://tekajeapunya.com/kelompok_11/carkus/getpendaftaran.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);

                                    array_id.add(jo.getString("id"));
                                    array_nama_paket.add(jo.getString("nama_paket"));
                                    array_mentor.add(jo.getString("mentor"));
                                    array_nama_pendaftar.add(jo.getString("nama_pendaftar"));
                                    array_NIK.add(jo.getString("NIK"));
                                    array_no_telp.add(jo.getString("no_telp"));
                                    array_foto.add(jo.getString("foto"));

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_Pendaftar adapter = new CLV_Pendaftar(List_Pendaftar_Activity.this,array_nama_paket, array_mentor, array_nama_pendaftar,
                                        array_NIK, array_no_telp,array_foto);
                                //Set adapter to list
                                listProses.setAdapter(adapter);

                                //edit and delete
//                                listProses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                        Log.d("TestKlik",""+array_nama_lengkap.get(position));
//                                        Toast.makeText(List_User_Activity.this, array_nama_lengkap.get(position), Toast.LENGTH_SHORT).show();
//
//                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
//                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
//                                        Intent i = new Intent(List_User_Activity.this, DetailDataUserAdminActivity.class);
//
//                                        i.putExtra("id",array_id.get(position));
//                                        i.putExtra("nama_pengguna",array_nama_pengguna.get(position));
//                                        i.putExtra("nama_lengkap",array_nama_lengkap.get(position));
//                                        i.putExtra("nim",array_nim.get(position));
//                                        i.putExtra("nohp",array_nohp.get(position));
//                                        i.putExtra("email",array_email.get(position));
//                                        i.putExtra("kata_sandi",array_kata_sandi.get(position));
//                                        i.putExtra("foto", array_foto.get(position));
//
//                                        startActivity(i);
//                                    }
//                                });


                            }else{
                                Toast.makeText(List_Pendaftar_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}