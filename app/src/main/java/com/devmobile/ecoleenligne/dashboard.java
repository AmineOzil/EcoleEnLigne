package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import Model.Utilisateur;

public class dashboard extends AppCompatActivity {

    ImageView img_profile;
    ImageView img_profile2;
    private static int selectionné =2;
    MeowBottomNavigation meo;
    final private int exams=1;
    final private int home=2;
    final private int forum=3;
    final private int profile=4;
    Fragment contenu=new DashboardFragment();
    static Utilisateur user;
    static String niveau;
    RelativeLayout fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user=(Utilisateur) getIntent().getSerializableExtra("USER");
        niveau=inscription.getStringNiveau(getIntent().getIntExtra("NIVEAU",new Integer(1)));
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

        meo.add(new MeowBottomNavigation.Model(exams, R.drawable.ic_exams));
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
            default:      Toast.makeText(dashboard.this,"Under construction",Toast.LENGTH_LONG).show();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.contenu,contenu).commit();
    }
    public static void getUserDetails(TextView tv){
        tv.setText(user.getNom()+" "+user.getPrénom());
    }

}