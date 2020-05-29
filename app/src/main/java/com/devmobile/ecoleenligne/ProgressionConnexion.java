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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Model.Progression;

public class ProgressionConnexion extends Fragment {


    TextView tvEspace_progression,nbr_connexion;
    ImageView icone_progression,img_profile, retour_menu;
    Progression progression;
    AdapterConnexion adapterConnexion;
    RecyclerView recycler_connexion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_connexion_history,container,false);
        if(dashboard.niveau.matches("Parent")) ((dashboard)getActivity()).selectedFromRetour(2);
        else ((dashboard)getActivity()).selectedFromRetour(1);
        if(dashboard.type!="Parent")
            progression=dashboard.eleve.getProgression();
        tvEspace_progression = view.findViewById(R.id.tvEspace_Progression);
        img_profile = view.findViewById(R.id.img_profile);
        retour_menu = view.findViewById(R.id.retour_menu);
        nbr_connexion=view.findViewById(R.id.nbr_connexions);
        nbr_connexion.setText(progression.getHistoriqueConnexion().size()+"");
        adapterConnexion=new AdapterConnexion(getActivity(),progression.getHistoriqueConnexion());
        recycler_connexion =view.findViewById(R.id.recycler_sessions_connexion);
        recycler_connexion.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_connexion.setHasFixedSize(true);
        recycler_connexion.setAdapter(adapterConnexion);
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




