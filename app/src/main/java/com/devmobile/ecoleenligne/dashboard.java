package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Eleve;
import Model.Eleve_parent;
import Model.Parent;

public class dashboard extends AppCompatActivity {

    ImageView img_profile;
    ImageView img_profile2;
    private static int selectionné =2;
    DatabaseReference mDatabase;
    MeowBottomNavigation meo;
    final private int progression=1;
    final private int home=2;
    final private int forum=3;
    final private int profile=4;
    Fragment contenu=new DashboardFragment();
    static Eleve eleve;
    static Parent parent;
    static String niveau;
    static String type;
    static ArrayList<Eleve_parent> listefils=new ArrayList<>();
    RelativeLayout fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        niveau=inscription.getStringNiveau(getIntent().getIntExtra("NIVEAU",new Integer(1)));
        type="Parent";
        if(niveau=="Parent"){
            parent=(Parent) getIntent().getSerializableExtra("USER");
            Log.v("Parent name :",parent.getNom());
            getFils();
            dashboard.super.onCreate(savedInstanceState);
        }else{
            eleve=(Eleve) getIntent().getSerializableExtra("USER");
            type=getIntent().getStringExtra("TYPE");
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        fm=findViewById(R.id.dashboard);
      /*  img_profile= findViewById(R.id.img_profile);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(dashboard.this,ProfileActivity.class);
                startActivity(profile);
            }
        })*/

        meo= (MeowBottomNavigation) findViewById(R.id.bottom_nav);

        meo.add(new MeowBottomNavigation.Model(progression, R.drawable.progression_fleche));
        meo.add(new MeowBottomNavigation.Model(home, R.drawable.ic_home));
        meo.add(new MeowBottomNavigation.Model(forum, R.drawable.ic_forum));
        meo.add(new MeowBottomNavigation.Model(profile, R.drawable.ic_user));
        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
               setContenu(item.getId());
            }
        });
        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        meo.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
        meo.show(selectionné,true);
        setContenu(selectionné);}
    }
    public void setContenu(int id){
        switch (id){
            case profile: contenu = new ProfileActivity();
                selectionné =profile;
                fm.setBackgroundResource(R.drawable.dashboard_bg2);
                break;
            case home:    contenu= new DashboardFragment();
                selectionné =home;
                fm.setBackgroundResource(R.drawable.dashboard);
                break;
            case forum:    contenu= new ForumFragment();
                selectionné =forum;
                fm.setBackgroundResource(R.drawable.dashboardcours);
                break;
            case progression:    contenu= new ProgressionMenu();
                selectionné =progression;
                fm.setBackgroundResource(R.drawable.dashboardcours);
                break;
            default:      Toast.makeText(dashboard.this,"Under construction",Toast.LENGTH_LONG).show();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.contenu,contenu).commit();
    }
    public void selectedFromRetour(int id){
        switch (id){
            case profile:
                selectionné =profile;
                fm.setBackgroundResource(R.drawable.dashboard_bg2);
                break;
            case home:
                selectionné =home;
                fm.setBackgroundResource(R.drawable.dashboard);
                break;
            case forum:
                selectionné =forum;
                fm.setBackgroundResource(R.drawable.dashboardcours);
                break;
            case progression:
                selectionné =progression;
                fm.setBackgroundResource(R.drawable.dashboardcours);
                break;
            default:      Toast.makeText(dashboard.this,"Under construction",Toast.LENGTH_LONG).show();
                break;
        }
        meo.show(selectionné,true);
    }
    public static void getUserDetails(TextView tv){
        if (niveau=="Parent") tv.setText(parent.getNom()+" "+parent.getPrénom());
        else tv.setText(eleve.getNom()+" "+eleve.getPrénom());
    }
    public void getFils(){
        mDatabase= FirebaseDatabase.getInstance().getReference("Elève_Parent");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                Eleve_parent eleve_parent = dataSnapshot1.getValue(Eleve_parent.class);
                if(eleve_parent.getId_parent()==parent.getId()) listefils.add(eleve_parent);
            }
                setContentView(R.layout.activity_dashboard);
                fm=findViewById(R.id.dashboard);
      /*  img_profile= findViewById(R.id.img_profile);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(dashboard.this,ProfileActivity.class);
                startActivity(profile);
            }
        })*/
                //J'ai ajouté cette partie pour assurer la synchronisation entre l'ajout des fils et l'affichage du dashboard

                meo= (MeowBottomNavigation) findViewById(R.id.bottom_nav);

               // meo.add(new MeowBottomNavigation.Model(progression, R.drawable.progression_fleche));
                meo.add(new MeowBottomNavigation.Model(home, R.drawable.ic_home));
                meo.add(new MeowBottomNavigation.Model(forum, R.drawable.ic_forum));
                meo.add(new MeowBottomNavigation.Model(profile, R.drawable.ic_user));
                meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
                    @Override
                    public void onClickItem(MeowBottomNavigation.Model item) {
                        setContenu(item.getId());
                    }
                });
                meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
                    @Override
                    public void onShowItem(MeowBottomNavigation.Model item) {
                        // your codes
                    }
                });

                meo.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
                    @Override
                    public void onReselectItem(MeowBottomNavigation.Model item) {
                        // your codes
                    }
                });
                meo.show(selectionné,true);
                setContenu(selectionné);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}