package com.devmobile.ecoleenligne;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Model.Eleve;
import Model.Eleve_parent;
import Model.Matière;
import Model.Parent;
import Model.Utilisateur;

public class inscription extends AppCompatActivity {
    Button suivant;
    EditText nom,prenom,email,password,confirm_password;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Spinner niveaux;
    Boolean firstTime=true;
    String profileUtilisateur;
    LinearLayout formulaire;
    Spinner spinner;

    public long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Bundle extras = getIntent().getExtras();
        profileUtilisateur = extras.getString("type");
        final ArrayAdapter<String> adapter = getSpinnerAdapterWithHint(this, R.layout.niveaux_spinner, Arrays.asList(getResources().getStringArray(R.array.niveaux)));
        adapter.setDropDownViewResource(R.layout.niveaux_etudes);
        niveaux = findViewById(R.id.spinner_niveaux);
        niveaux.setAdapter(adapter);
        final ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams) niveaux.getLayoutParams();
        params.width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,282,getResources().getDisplayMetrics());
        params.height= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,39,getResources().getDisplayMetrics());
        params.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics());;
        final int paddingLeftEditText=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30,getResources().getDisplayMetrics());
        formulaire= findViewById(R.id.formulaire_signup);
        if(profileUtilisateur.matches("Parent")){
            formulaire.removeViewAt(5);

            ArrayList<String> nbr_fils=new ArrayList<String>(Arrays.asList("Nombre d'enfants","1","2","3","4"));
            ArrayList<String> liens_parenté=new ArrayList<>(Arrays.asList("Lien de parenté","Père","Mère","Soeur","Frère","Oncle","Tante","Grand-père","Grande mère"));
            final ArrayAdapter<String> liens_adapter=getSpinnerAdapterWithHint(this,R.layout.niveaux_spinner,liens_parenté);
            spinner = new Spinner(this);
            ArrayAdapter<String> fils_adapter= getSpinnerAdapterWithHint(this,R.layout.niveaux_spinner,nbr_fils);
            fils_adapter.setDropDownViewResource(R.layout.niveaux_etudes);
            liens_adapter.setDropDownViewResource(R.layout.niveaux_etudes);
            spinner.setAdapter(fils_adapter);
            formulaire.addView(spinner);
            spinner.setLayoutParams(params);
            spinner.setTag(spinner);
            spinner.setBackgroundResource(R.drawable.round_edit_signup);
            spinner.setPadding(params.bottomMargin,0,10,0);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0) return;
                    int nbr= Integer.parseInt(spinner.getSelectedItem().toString());
                    if(!firstTime)
                    cleanLayout(formulaire);
                    for (int i=0;i<nbr;i++){
                        EditText editTextNom=new EditText(inscription.this);
                        formulaire.addView(editTextNom);
                       editTextNom.setBackgroundResource(R.drawable.round_edit_signup);
                       editTextNom.setLayoutParams(params);
                       editTextNom.setPadding(paddingLeftEditText,0,10,0);
                        editTextNom.setHint("Nom de votre "+(i+1)+" proche");
                        editTextNom.setTypeface(ResourcesCompat.getFont(inscription.this,R.font.calibri_regular));
                        editTextNom.setTag(editTextNom);
                        EditText editTextPrenom=new EditText(inscription.this);
                        formulaire.addView(editTextPrenom);
                        editTextPrenom.setBackgroundResource(R.drawable.round_edit_signup);
                        editTextPrenom.setLayoutParams(params);
                        editTextPrenom.setPadding(paddingLeftEditText,0,10,0);
                        editTextPrenom.setHint("Prénom de votre "+(i+1)+" proche");
                        editTextPrenom.setTypeface(ResourcesCompat.getFont(inscription.this,R.font.calibri_regular));
                        editTextPrenom.setTag(editTextPrenom);
                        Spinner niveau_fils=new Spinner(inscription.this);
                        niveau_fils.setAdapter(adapter);
                        formulaire.addView(niveau_fils);
                        niveau_fils.setBackgroundResource(R.drawable.round_edit_signup);
                        niveau_fils.setLayoutParams(params);
                        niveau_fils.setPadding(params.bottomMargin,0,10,0);
                        niveau_fils.setTag(niveau_fils);
                        Spinner lien_parenté=new Spinner(inscription.this);
                        lien_parenté.setAdapter(liens_adapter);
                        formulaire.addView(lien_parenté);
                        lien_parenté.setBackgroundResource(R.drawable.round_edit_signup);
                        lien_parenté.setLayoutParams(params);
                        lien_parenté.setPadding(params.bottomMargin,0,10,0);
                        lien_parenté.setTag(lien_parenté);
                        EditText editTextUsername=new EditText(inscription.this);
                        formulaire.addView(editTextUsername);
                        editTextUsername.setBackgroundResource(R.drawable.round_edit_signup);
                        editTextUsername.setLayoutParams(params);
                        editTextUsername.setPadding(paddingLeftEditText,0,10,0);
                        setUsernameField(editTextNom,editTextPrenom,editTextUsername,i+1);
                        editTextUsername.setTypeface(ResourcesCompat.getFont(inscription.this,R.font.calibri_regular));
                        editTextUsername.setTag(editTextUsername);
                        editTextUsername.setEnabled(false);
                        editTextUsername.setHint("identifiant:password générés");


                    }
                    firstTime=false;
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
        auth = FirebaseAuth.getInstance();
        suivant = findViewById(R.id.signup_suivant);
        nom = findViewById(R.id.signup_nom);
        prenom = findViewById(R.id.signup_prenom);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_mdp);
        confirm_password = findViewById(R.id.signup_confirmmdp);
        EditText editTextIdentifiant=new EditText(inscription.this);
        formulaire.addView(editTextIdentifiant,5);
        editTextIdentifiant.setBackgroundResource(R.drawable.round_edit_signup);
        editTextIdentifiant.setLayoutParams(params);
        editTextIdentifiant.setPadding(paddingLeftEditText,0,10,0);
        editTextIdentifiant.setHint("Identifiant");
        editTextIdentifiant.setTypeface(ResourcesCompat.getFont(inscription.this,R.font.calibri_regular));
        editTextIdentifiant.setTag(editTextIdentifiant);

        databaseReference = FirebaseDatabase.getInstance().getReference("Utilisateur");


        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Nom = nom.getText().toString();
                final String Prenom = prenom.getText().toString();
                final String Email = email.getText().toString();
                final String Identifiant= ((EditText)formulaire.getChildAt(5).getTag()).getText().toString().trim();
                final String Password = password.getText().toString();
                String ConfirmPassword = confirm_password.getText().toString();
                if(!ConfirmPassword.matches(Password)){ confirm_password.setError("Mot de passes non identiques");
                confirm_password.requestFocus();
                return;}
                if(Password.length()<6){
                    password.setError("Mot de passes doit contenir au moins 6 caractères");
                    password.requestFocus();
                }
                final int Niveau = getIdNiveau(niveaux.getSelectedItem().toString().trim());

                if(profileUtilisateur.matches("Parent")){
                    int nbr_fils=Integer.parseInt(((Spinner)formulaire.getChildAt(6).getTag()).getSelectedItem().toString());
                    Parent p=new Parent(Nom,Prenom,Identifiant,Password,nbr_fils,Email);
                    ArrayList<Eleve_parent> eleves=new ArrayList<>();
                    for(int i=1;i<=nbr_fils*5;i+=5){

                        String nom=((EditText) formulaire.getChildAt(i+6).getTag()).getText().toString();
                        String prenom=((EditText) formulaire.getChildAt(i+7).getTag()).getText().toString();
                        String niveau_fils=((Spinner) formulaire.getChildAt(i+8).getTag()).getSelectedItem().toString();
                        String lien=((Spinner) formulaire.getChildAt(i+9).getTag()).getSelectedItem().toString();
                        String username=((EditText) formulaire.getChildAt(i+10).getTag()).getText().toString().split(":")[0];
                        String password=((EditText) formulaire.getChildAt(i+10).getTag()).getText().toString().split(":")[1];
                        Eleve_parent fils=new Eleve_parent(nom,prenom,username,password,p.getId(),getIdNiveau(niveau_fils),lien,p.getEmail(),(i/5)+1);
                        eleves.add(fils);
                    }
                    addParentetEleves(p,eleves);
                } else{
                    Eleve eleve=new Eleve(Nom,Prenom,Identifiant,Password,Niveau,Email);
                    ajouterUtilisateur(eleve,"Elève");
                }

            }
        });

    }
    public void ajouterUtilisateur(final Utilisateur u, final String type){
        getUsersCount(u,"Elève");
        getUsersCount(u,"Parent");
        getUsersCount(u,"Elève_Parent");
        auth.createUserWithEmailAndPassword(u.getEmail(),u.getMot_de_passe()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                        user = auth.getCurrentUser();
                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseDatabase.getInstance().getReference("Elève")
                                                .push().setValue(u.getIdentifiant()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(inscription.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                                                auth.signOut();
                                                Intent success = new Intent(inscription.this, checkEmail.class);
                                                startActivity(success);
                                            }
                                        });
                                    } else {

                                    }

                                }
                            });
                }else {
                }
            }
        });

        //auth.signOut();
    }
    public void ajouterMatière(final Matière m){

        FirebaseDatabase.getInstance().getReference("Matière")
                                                .push().setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(inscription.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                                                auth.signOut();
                                                Intent success = new Intent(inscription.this, checkEmail.class);
                                                startActivity(success);
                                            }
                                        });


        //auth.signOut();
    }


    public void addParentetEleves(final Parent p,final ArrayList<Eleve_parent> e){
        getUsersCount(p,"Elève");
        getUsersCount(p,"Parent");
        getUsersCount(p,"Elève_Parent");
        auth.createUserWithEmailAndPassword(p.getEmail(),p.getMot_de_passe()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                        user = auth.getCurrentUser();
                        user.sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            FirebaseDatabase.getInstance().getReference("Parent")
                                                    .push().setValue(p.getIdentifiant()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(inscription.this, "Inscription du parent effectuée", Toast.LENGTH_SHORT).show();
                                                    addFils(0,e,p);
                                                    auth.signOut();
                                                    Intent success = new Intent(inscription.this, checkEmail.class);
                                                    startActivity(success);
                                            }


                                });

                    }
                }

        });
    }
            }});}
    public void addFils(final int i,final ArrayList<Eleve_parent> fils,final Parent p){
        if(i==fils.size()){
            Toast.makeText(inscription.this, "Inscription des fils effectuée", Toast.LENGTH_SHORT).show();

        }
        else{
            getUsersCount(fils.get(i),"Elève");
            getUsersCount(fils.get(i),"Parent");
            getUsersCount(fils.get(i),"Elève_Parent");
            fils.get(i).setId_parent(p.getId());
            auth.createUserWithEmailAndPassword(fils.get(i).getEmail(),fils.get(i).getMot_de_passe()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseDatabase.getInstance().getReference("Elève_Parent")
                                .push().setValue(fils.get(i).getIdentifiant()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                addFils(i+1,fils,p);
                            }
                        });
                    }
                }
            });

        }

    }
    public void getUsersCount(final Utilisateur u,final String type) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(type);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u.setId(u.getId()+dataSnapshot.getChildrenCount());
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void cleanLayout(LinearLayout layout){
        int acleaner=layout.getChildCount()-1;
        Log.v("",acleaner+"");
        while(acleaner>=7){
            layout.removeViewAt(acleaner);
            acleaner--;
        }
    }
    public String generateUsername(String nom,String prénom){
        Boolean[] exist=new Boolean[3];
        exist[0]=false;
        exist[1]=false;
        exist[2]=false;
        String username="";
        do{
            username=nom.toLowerCase()+"."+prénom.toLowerCase()+ (new Random().nextInt(30)+1);
            checkUsername("Elève",username,exist);
            checkUsername("Parent",username,exist);
            checkUsername("Elève_Parent",username,exist);
        }while(exist[0]||exist[1]||exist[2]);
        return username;
    }
    public void setUsernameField(final EditText nom, final EditText prénom, final EditText username, final int fils_rang){
        nom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username.setEnabled(true);
                if(s.length()!=0)
                    if(!(prénom.getText().toString().trim().matches(""))) username.setText(generateUsername(nom.getText().toString().trim(),prénom.getText().toString().trim())+":"+generatePassword().trim());
                username.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        prénom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username.setEnabled(true);
                if(s.length()!=0)
                    if(!(nom.getText().toString().trim().matches(""))) username.setText(generateUsername(nom.getText().toString().trim(),prénom.getText().toString().trim())+":"+generatePassword().trim());
                username.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void checkUsername(final String type_user, final String username, final Boolean[] exist){
        Log.v("checking if"+username+" exists"," in table: "+type_user);
        DatabaseReference users = FirebaseDatabase.getInstance().getReference(type_user);
        Query query = users.orderByChild("identifiant").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()>0){
                    if(type_user.matches("Elève"))
                        exist[0] =true;
                    else if(type_user.matches("Parent"))
                        exist[1]=true;
                    else if(type_user.matches("Elève_Parent"))
                        exist[2]=true;
                    Log.v(username," exists");
                }
                Log.v(exist[0]+" "+exist[1]+" "+exist[2],"");



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public int getIdNiveau(String niveau){
        if(niveau.matches("Primaire")) return 1;
        else if (niveau.matches("Seconde")) return 2;
        else if (niveau.matches("Terminal L")) return 3;
        else if (niveau.matches("Terminal S")) return 4;
        else return 0;
    }
    public static String getStringNiveau(int niveau){
        if(niveau==1) return "Primaire";
        else if (niveau==2) return "Seconde";
        else if (niveau==3) return "Terminal L";
        else if (niveau==4) return "Terminal S";
        else return "Parent";
    }
    public ArrayAdapter<String> getSpinnerAdapterWithHint(Context t, @LayoutRes int res, @NonNull List<String> obj){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(t,res,obj){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        return adapter;
    }
    public String generatePassword(){
        final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

            final Random random=new Random();
            final StringBuilder sb=new StringBuilder(6);

            for(int i=0;i<6;++i){
                sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
            }
        return sb.toString();

    }
}
