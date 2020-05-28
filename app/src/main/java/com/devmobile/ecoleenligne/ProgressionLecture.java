package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import Model.Progression;

public class ProgressionLecture extends Fragment {


    TextView tvEspace_progression;
    ImageView icone_progression,img_profile, retour_menu;
    Progression progression;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chapitres_lus,container,false);

        tvEspace_progression = view.findViewById(R.id.tvEspace_Progression);
        img_profile = view.findViewById(R.id.img_profile);
        retour_menu = view.findViewById(R.id.retour_menu);



        retour_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment profile =new ProfileActivity();
                ft.replace(R.id.contenu,profile).addToBackStack(null);
                ft.commit();
            }
        });




        return view;
    }

    public void setProgression(Progression c){
        this.progression=c;
    }
    public Progression getProgression(){
        return this.progression;
    }

}




