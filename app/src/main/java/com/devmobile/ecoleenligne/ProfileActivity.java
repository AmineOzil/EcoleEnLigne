package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    ImageView eleve;
    ImageView enseignant;
    ImageView parent;
    Button suivant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        suivant = findViewById(R.id.bt_suivant_etape1);
        eleve = findViewById(R.id.img_eleve);
        enseignant = findViewById(R.id.img_enseignant);
        parent = findViewById(R.id.img_parent);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ProfileActivity.this,inscription.class);
                startActivity(login);
            }
        });
        eleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Elève !",Toast.LENGTH_LONG).show();
            }
        });
        enseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Enseignant !",Toast.LENGTH_LONG).show();
            }
        });
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Parent !",Toast.LENGTH_LONG).show();
            }
        });
    }
}