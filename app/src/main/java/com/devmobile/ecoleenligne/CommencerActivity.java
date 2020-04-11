package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CommencerActivity extends AppCompatActivity {
    Button authentification;
    Button inscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commencer);
        authentification= findViewById(R.id.bt_login);
        inscription= findViewById(R.id.bt_inscription);
        authentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(CommencerActivity.this,login.class);
                startActivity(login);
            }
        });
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(CommencerActivity.this,ProfileActivity.class);
                startActivity(login);
            }
        });
    }
}