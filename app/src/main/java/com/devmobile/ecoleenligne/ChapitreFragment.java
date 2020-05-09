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

public class ChapitreFragment extends Fragment {

    Button deconnexion;
    ImageView retour;
    FirebaseAuth mAuth;
    TextView nom_prénom;
    TextView email, niveau;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        deconnexion = view.findViewById(R.id.bt_deconnexion);
        email = view.findViewById(R.id.tvEmail);
        niveau = view.findViewById(R.id.tvNiveau);
        retour = view.findViewById(R.id.retour_profile);
        nom_prénom = view.findViewById(R.id.tvProfile);
        dashboard.getUserDetails(nom_prénom);
        email.setText(dashboard.user.getEmail());
        niveau.setText(dashboard.niveau);
        mAuth = FirebaseAuth.getInstance();
        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent deconnect = new Intent(getActivity(), login.class);
                startActivity(deconnect);
                mAuth.signOut();
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment dash = new DashboardFragment();
                ft.replace(R.id.contenu, dash);
                ft.commit();
            }
        });
        return view;
    }
}