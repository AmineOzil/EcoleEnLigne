package com.devmobile.ecoleenligne;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Eleve;
import Model.Eleve_parent;
import Model.Parent;
import Model.Utilisateur;

public class login extends AppCompatActivity {

    EditText username, password;
    Button login;
    private FirebaseAuth mAuth;
    private FirebaseUser futilisateur;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;
    String TAG="Debuging";

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

   /* @Override
    protected void onStart() {
        super.onStart();
     mAuth.addAuthStateListener(mAuthListener);
    }
    */

    private void startSignIn(){

        final String[] user = {username.getText().toString().trim()};
        final String pass = password.getText().toString().trim();

        if(TextUtils.isEmpty(user[0]) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Les champs sont vides", Toast.LENGTH_SHORT).show();
        }else{
            Log.v(isEmail(user[0])+"","");
            if(!isEmail(user[0])){
                loginWithUsername(user,pass);
            }else{
                mAuth.signInWithEmailAndPassword(user[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        futilisateur=mAuth.getCurrentUser();
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            if(futilisateur.isEmailVerified()){
                                finish();
                                startActivity(new Intent(login.this, dashboard.class));

                            }else{
                                Toast.makeText(login.this,"Veuillez vérifier votre émail",Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                            }
                        }
                    }
                });
            }
        }
    }
    public boolean isEmail(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void loginWithUsername(final String[] user,final String pass){
        final String[] Email = {""};
        Log.v("checking if"+user[0]+" exists"," in table: "+"Elève");
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("Elève_Parent");
        Query query = users.orderByChild("identifiant").equalTo(user[0]);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        final Eleve_parent utilisateur= ds.getValue(Eleve_parent.class);
                        Toast.makeText(login.this,utilisateur.getNom()+" "+utilisateur.getPrénom()+" "+utilisateur.getEmail()+"",Toast.LENGTH_SHORT).show();
                        Email[0] =utilisateur.getEmail();
                        progressDialog.setMessage("Signing In Please Wait..");
                        progressDialog.show();

                        mAuth.signInWithEmailAndPassword(Email[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    finish();
                                    Intent intent=new Intent(login.this, dashboard.class);
                                    intent.putExtra("USER", utilisateur);
                                    intent.putExtra("NIVEAU", utilisateur.getId_niveau());
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }else{
                    DatabaseReference users = FirebaseDatabase.getInstance().getReference("Elève");
                    Query query = users.orderByChild("identifiant").equalTo(user[0]);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    final Eleve utilisateur= ds.getValue(Eleve.class);
                                    Toast.makeText(login.this,utilisateur.getNom()+" "+utilisateur.getPrénom()+" "+utilisateur.getEmail()+"",Toast.LENGTH_SHORT).show();
                                    Email[0] =utilisateur.getEmail();
                                    progressDialog.setMessage("Signing In Please Wait..");
                                    progressDialog.show();

                                    mAuth.signInWithEmailAndPassword(Email[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            futilisateur=mAuth.getCurrentUser();
                                            progressDialog.dismiss();
                                            if (task.isSuccessful()) {
                                                if(futilisateur.isEmailVerified()){
                                                finish();
                                                    Intent intent=new Intent(login.this, dashboard.class);
                                                    intent.putExtra("USER", utilisateur);
                                                    intent.putExtra("NIVEAU", utilisateur.getId_niveau());
                                                    startActivity(intent);
                                                }else{
                                                    Toast.makeText(login.this,"Veuillez vérifier votre émail",Toast.LENGTH_SHORT).show();
                                                    mAuth.signOut();
                                                }
                                            }
                                        }
                                    });
                                }
                            }else{
                                DatabaseReference users = FirebaseDatabase.getInstance().getReference("Parent");
                                Query query = users.orderByChild("identifiant").equalTo(user[0]);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){
                                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                final Parent utilisateur= ds.getValue(Parent.class);
                                                Toast.makeText(login.this,utilisateur.getNom()+" "+utilisateur.getPrénom()+" "+utilisateur.getEmail()+"",Toast.LENGTH_SHORT);
                                                Email[0] =utilisateur.getEmail();
                                                progressDialog.setMessage("Signing In Please Wait..");
                                                progressDialog.show();

                                                mAuth.signInWithEmailAndPassword(Email[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                                        futilisateur=mAuth.getCurrentUser();
                                                        progressDialog.dismiss();
                                                        if (task.isSuccessful()) {
                                                            if(futilisateur.isEmailVerified()){
                                                                finish();
                                                                Intent intent=new Intent(login.this, dashboard.class);
                                                                intent.putExtra("USER", utilisateur);
                                                                intent.putExtra("NIVEAU", 5);
                                                                startActivity(intent);

                                                            }else{
                                                                Toast.makeText(login.this,"Veuillez vérifier votre émail",Toast.LENGTH_SHORT).show();
                                                                mAuth.signOut();
                                                            }
                                                        }
                                                    }
                                                });
                                            }
                                        }else{
                                            Toast.makeText(login.this,"Email doesn't exist",Toast.LENGTH_SHORT);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}