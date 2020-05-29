package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends Fragment {
    Button deconnexion;
    ImageView retour, img_forum;
    FirebaseAuth mAuth;
    TextView nom_prénom;
    TextView email,niveau;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_profile,container,false);
        ((dashboard)getActivity()).selectedFromRetour(4);
        deconnexion= view.findViewById(R.id.bt_deconnexion);
        email=view.findViewById(R.id.tvEmail);
        niveau=view.findViewById(R.id.tvNiveau);
        retour= view.findViewById(R.id.retour_profile);
        img_forum=view.findViewById(R.id.img_forum);
        nom_prénom=view.findViewById(R.id.tvProfile);
        dashboard.getUserDetails(nom_prénom);
        if(dashboard.niveau=="Parent")  email.setText(dashboard.parent.getEmail());
        else email.setText(dashboard.eleve.getEmail());
        niveau.setText(dashboard.niveau);
        mAuth = FirebaseAuth.getInstance();
        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent deconnect = new Intent(getActivity(),login.class);
                startActivity(deconnect);
                mAuth.signOut();
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();

            }
        });
        img_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment forum =new ForumFragment();
                ft.replace(R.id.contenu,forum).addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}