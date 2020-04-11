package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class checkEmail extends AppCompatActivity {
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_termine);
        check = findViewById(R.id.bt_verifierEmail);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(checkEmail.this, login.class);
                startActivity(signIn);
            }
        });
    }
}
