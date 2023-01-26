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

public class CLV_Pendaftar extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vNamapaket;
    private ArrayList<String> vMentor;
    private ArrayList<String> vNamapendaftar;
    private ArrayList<String> vNIK;
    private ArrayList<String> vNo_telp;
    private ArrayList<String> vFoto;


    public CLV_Pendaftar(Activity context, ArrayList<String> Namapaket,ArrayList<String> Mentor, ArrayList<String> Namapendaftar, ArrayList<String> NIK, ArrayList<String> No_telp, ArrayList<String> Foto) {
        super(context, R.layout.activity_list_pendaftar,Namapaket);
        this.context    = context;
        this.vNamapaket = Namapaket;
        this.vMentor = Mentor;
        this.vNamapendaftar = Namapendaftar;
        this.vNIK  = NIK;
        this.vNo_telp      = No_telp;
        this.vFoto      = Foto;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_pendaftar, null, true);

        //Declarasi komponen
        TextView nama_paket             = rowView.findViewById(R.id.nama_paket);
        TextView mentor             = rowView.findViewById(R.id.ETUp_mentor);
        TextView nama_pendaftar             = rowView.findViewById(R.id.nama_pendaftar);
        TextView NIK          = rowView.findViewById(R.id.id_nik);
        TextView no_telp            = rowView.findViewById(R.id.nohp);
        CircleImageView foto      = rowView.findViewById(R.id.pic_profil_staff);

        //Set Parameter Value sesuai widget textview
        nama_paket.setText(vNamapaket.get(position));
        mentor.setText(vMentor.get(position));
        nama_pendaftar.setText(vNamapendaftar.get(position));
        NIK.setText(vNIK.get(position));
        no_telp.setText(vNo_telp.get(position));

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