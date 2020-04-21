package com.devmobile.ecoleenligne;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText username, password;
    Button login;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(login.this, dashboard.class));
                }
            }
        };

        progressDialog = new ProgressDialog(this);
        username = (EditText) findViewById(R.id.edUsername);
        password = (EditText) findViewById(R.id.edPassword);
        login = findViewById(R.id.bt_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
     mAuth.addAuthStateListener(mAuthListener);
    }


    private void startSignIn(){

        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.setMessage("Signing In Please Wait..");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(login.this, dashboard.class));
                    }else{

                        Toast.makeText(login.this, "Email ou mot de passe incorrects ! veuillez ré-essayer SVP", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}