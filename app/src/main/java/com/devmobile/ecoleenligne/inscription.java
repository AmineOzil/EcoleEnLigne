package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Eleve;

public class inscription extends AppCompatActivity {
    Button suivant;
    EditText nom,prenom,email,password,confirm_password;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Spinner niveaux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        auth = FirebaseAuth.getInstance();

        suivant = findViewById(R.id.signup_suivant);
        nom = findViewById(R.id.signup_nom);
        prenom = findViewById(R.id.signup_prenom);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_mdp);
        confirm_password = findViewById(R.id.signup_confirmmdp);
        niveaux = findViewById(R.id.spinner_niveaux);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.niveaux_spinner,
                getResources().getStringArray(R.array.niveaux)
        );
        adapter.setDropDownViewResource(R.layout.niveaux_etudes);
        niveaux.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Utilisateur");


        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle extras = getIntent().getExtras();
                final int profileUtilisateur = extras.getInt("Elève");
                final String Nom = nom.getText().toString();
                final String Prenom = prenom.getText().toString();
                final String Identifiant = email.getText().toString();
                final String Password = password.getText().toString();
                String ConfirmPassword = confirm_password.getText().toString();
                final String Niveau = niveaux.getSelectedItem().toString().trim();
                Toast.makeText(inscription.this,Niveau,Toast.LENGTH_LONG).show();
                int id_niveau = 0;

                if(TextUtils.isEmpty(Nom)){
                    Toast.makeText(inscription.this, "Veuillez saisir votre nom svp", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Prenom)){
                    Toast.makeText(inscription.this, "Veuillez saisir votre prénom svp", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Identifiant)){
                    Toast.makeText(inscription.this, "Veuillez saisir votre identifiant svp", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Password)){
                    Toast.makeText(inscription.this, "Veuillez saisir votre mot de passe svp", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(ConfirmPassword)){
                    Toast.makeText(inscription.this, "Veuillez confirmer votre mot de passe svp", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Niveau)){
                    Toast.makeText(inscription.this, "Veuillez sélectionner votre niveau svp", Toast.LENGTH_SHORT).show();
                }

                if(Niveau.matches("Primaire")){
                    id_niveau = 1;
                }
                if(Niveau.matches("Seconde")){
                    id_niveau = 2;
                }
                if(Niveau.matches("Terminal L")){
                    id_niveau = 3;
                }
                if(Niveau.matches("Terminal S")){
                    id_niveau = 4;
                }


                final int finalId_niveau = id_niveau;

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

                                             if(profileUtilisateur == 1){

                                                 Eleve u = new Eleve(Nom,Prenom,Identifiant,Password,finalId_niveau);

                                                 FirebaseDatabase.getInstance().getReference("Elève")
                                                         .child(auth.getInstance().getCurrentUser().getUid())
                                                         .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                     @Override
                                                     public void onComplete(@NonNull Task<Void> task) {
                                                         Toast.makeText(inscription.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                                                         Intent success = new Intent(inscription.this, checkEmail.class);
                                                         startActivity(success);
                                                     }
                                                 });
                                             }
                                         
                                           /*if(Niveau.matches("Enseignant")){

                                             }
                                             if(Niveau.matches("Parent")){

                                             }*/


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
