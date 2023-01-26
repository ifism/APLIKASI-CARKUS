package com.example.campin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CLV_Paket extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama_paket;
    private ArrayList<String> vjumlah_pertemuan;
    private ArrayList<String> vharga;
    private ArrayList<String> vmentor;
    private ArrayList<String> vFoto;


    public CLV_Paket(Activity context, ArrayList<String> nama_paket, ArrayList<String> jumlah_pertemuan, ArrayList<String> harga,ArrayList<String> mentor, ArrayList<String> Foto) {
        super(context, R.layout.list_paket,nama_paket);
        this.context    = context;
        this.vnama_paket = nama_paket;
        this.vjumlah_pertemuan  = jumlah_pertemuan;
        this.vharga      = harga;
        this.vmentor      = mentor;
        this.vFoto      = Foto;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_paket, null, true);

        //Declarasi komponen
        TextView nama_paket             = rowView.findViewById(R.id.nama_paket);
        TextView jumlah_pertemuan          = rowView.findViewById(R.id.jumlah_pertemuan);
        TextView harga             = rowView.findViewById(R.id.harga);
        TextView mentor             = rowView.findViewById(R.id.mentor);
        CircleImageView foto      = rowView.findViewById(R.id.pic_paket);

        //Set Parameter Value sesuai widget textview
        nama_paket.setText(vnama_paket.get(position));
        jumlah_pertemuan.setText(vjumlah_pertemuan.get(position));
        harga.setText(vharga.get(position));
        mentor.setText(vmentor.get(position));

        if (vFoto.get(position).equals(""))
        {
            Picasso.get().load("https://tekajeapunya.com/kelompok_11/carkus/noimages.png").into(foto);
        }
        else
        {
            Picasso.get().load("https://tekajeapunya.com/kelompok_11/image/"+vFoto.get(position)).into(foto);
        }


        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }



}