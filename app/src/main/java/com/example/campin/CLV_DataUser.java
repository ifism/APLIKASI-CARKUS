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

public class CLV_DataUser extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vNamaPaket;
    private ArrayList<String> vNamaPendaftar;
    private ArrayList<String> vNIK;
    private ArrayList<String> vNoTelp;
    private ArrayList<String> vFoto;

    public CLV_DataUser(Activity context, ArrayList<String> vNamaPaket, ArrayList<String> vNamaPendaftar,
                        ArrayList<String> NIK, ArrayList<String> NoTelp, ArrayList<String> array_foto) {
        super(context, R.layout.list_item_user,vNamaPendaftar);
        this.context = context;
        this.vNamaPaket = vNamaPaket;
        this.vNamaPendaftar = vNamaPendaftar;
        this.vNIK = NIK;
        this.vNoTelp = NoTelp;
        //this.vFoto = Foto;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item_user, null, true);

        //Declarasi komponen
        TextView name             = rowView.findViewById(R.id.nama_user);
        TextView nim              = rowView.findViewById(R.id.nim_user);
        CircleImageView foto      = rowView.findViewById(R.id.pic_profil_user);

        //Set Parameter Value sesuai widget textview
        name.setText(vNamaPendaftar.get(position));
        nim.setText(vNIK.get(position));

        if (vFoto.get(position).equals(""))
        {
            Picasso.get().load("https://tkjalpha19.com/mobile/image/noimages.png").into(foto);
        }
        else
        {
            Picasso.get().load("https://tkjalpha19.com/mobile/image/"+vFoto.get(position)).into(foto);
        }


        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }



}
