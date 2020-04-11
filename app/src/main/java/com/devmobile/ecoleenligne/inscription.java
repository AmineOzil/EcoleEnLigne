package com.devmobile.ecoleenligne;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class inscription extends AppCompatActivity {
    Button suivant;
    EditText email,password;
    FirebaseAuth auth;
    FirebaseUser user;
    Spinner niveaux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        auth = FirebaseAuth.getInstance();

        suivant = findViewById(R.id.signup_suivant);
        email = findViewById(R.id.signup_mail);
        password = findViewById(R.id.signup_mdp);
        niveaux = findViewById(R.id.spinner_niveaux);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.niveaux_spinner,
                getResources().getStringArray(R.array.niveaux)
        );
        adapter.setDropDownViewResource(R.layout.niveaux_etudes);
        niveaux.setAdapter(adapter);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                         user = auth.getCurrentUser();
                         user.sendEmailVerification()
                                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if (task.isSuccessful()) {
                                             Toast.makeText(inscription.this,"Utilisateur ajouté veuillez vérifier votre mail",Toast.LENGTH_SHORT).show();
                                         }
                                         else {
                                             Toast.makeText(inscription.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                         }
                                     }
                                 });
                     }else {
                         Toast.makeText(inscription.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                     }
                 }
             });
            }
        });

    }
}
