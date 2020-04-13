package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dashboard extends AppCompatActivity {

    ImageView img_profile;
    ImageView img_profile2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        img_profile= findViewById(R.id.img_profile);
        img_profile2 = findViewById(R.id.img_profile2);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(dashboard.this,ProfileActivity.class);
                startActivity(profile);
            }
        });
        img_profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile2 = new Intent(dashboard.this, ProfileActivity.class);
                startActivity(profile2);
            }
        });
    }
}