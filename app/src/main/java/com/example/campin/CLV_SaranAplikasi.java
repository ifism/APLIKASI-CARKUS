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

public class CLV_SaranAplikasi extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama;
    private ArrayList<String> vemail;
    private ArrayList<String> vsaran;


    public CLV_SaranAplikasi(Activity context, ArrayList<String> nama, ArrayList<String> email, ArrayList<String> saran) {
        super(context, R.layout.list_saranaplikasi,nama);
        this.context    = context;
        this.vnama = nama;
        this.vemail  = email;
        this.vsaran      = saran;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_saranaplikasi, null, true);

        //Declarasi komponen
        TextView nama_paket             = rowView.findViewById(R.id.nama);
        TextView email          = rowView.findViewById(R.id.email);
        TextView saran             = rowView.findViewById(R.id.saran);

        //Set Parameter Value sesuai widget textview
        nama_paket.setText(vnama.get(position));
        email.setText(vemail.get(position));
        saran.setText(vsaran.get(position));


        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }



}