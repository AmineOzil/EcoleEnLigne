package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreinscriptionActivity extends AppCompatActivity {
    ImageView eleve;
    ImageView enseignant;
    ImageView parent;
    Button suivant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preinscription);

        eleve = findViewById(R.id.img_eleve);
        enseignant = findViewById(R.id.img_enseignant);
        parent = findViewById(R.id.img_parent);


        eleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Elève !",Toast.LENGTH_LONG).show();
                Intent inscription = new Intent(PreinscriptionActivity.this,inscription.class);
                inscription.putExtra("Elève",1);
                startActivity(inscription);
            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Parent !",Toast.LENGTH_LONG).show();
                Intent inscription = new Intent(PreinscriptionActivity.this,inscription.class);
                inscription.putExtra("Parent",2);
                startActivity(inscription);
            }
        });
    }
}