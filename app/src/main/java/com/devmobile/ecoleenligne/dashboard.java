package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class dashboard extends AppCompatActivity {

    ImageView img_profile;
    ImageView img_profile2;
    MeowBottomNavigation meo;
    private int exams=1;
    private int home=2;
    private int forum=3;
    private int profile=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        img_profile= findViewById(R.id.img_profile);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(dashboard.this,ProfileActivity.class);
                startActivity(profile);
            }
        });
        /*img_profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile2 = new Intent(dashboard.this, ProfileActivity.class);
                startActivity(profile2);
            }
        });*/
        meo= (MeowBottomNavigation) findViewById(R.id.bottom_nav);

        meo.add(new MeowBottomNavigation.Model(exams, R.drawable.ic_exams));
        meo.add(new MeowBottomNavigation.Model(home, R.drawable.ic_home));
        meo.add(new MeowBottomNavigation.Model(forum, R.drawable.ic_forum));
        meo.add(new MeowBottomNavigation.Model(profile, R.drawable.ic_user));
        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if (item.getId()==profile){
                    Intent profile2 = new Intent(dashboard.this, ProfileActivity.class);
                    startActivity(profile2);
                }
                else
                Toast.makeText(dashboard.this,"Under construction",Toast.LENGTH_LONG).show();
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
        meo.show(home,true);
    }
}