package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class whatsapp extends AppCompatActivity {

    Button button;
    EditText Edtpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);

        Edtpesan = findViewById(R.id.editpesan);

        button = findViewById(R.id.btnkirim);
        button.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String pesan4 = Edtpesan.getText().toString();

                String semuapesan =" " + pesan4;

                Intent kirimWA = new Intent(Intent.ACTION_SEND);
                kirimWA.setType("text/plain");
                kirimWA.putExtra(Intent.EXTRA_TEXT, semuapesan);
                kirimWA.putExtra("jid", "6285796075901" + "@s.whatsapp.net");
                kirimWA.setPackage("com.whatsapp");

                startActivity(kirimWA);
            }
        });

    }
}