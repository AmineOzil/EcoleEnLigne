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

public class ProgressionLecture extends Fragment {


    TextView tvEspace_progression;
    ImageView icone_progression,img_profile, retour_menu;
    Progression progression;
    TextView nbr_matières, nbr_chapitres,matières_lu;
    RecyclerView recycler_chapitres_lus;
    AdapterChapitreLu adapterChapitreLu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chapitres_lus,container,false);
        if(dashboard.niveau.matches("Parent")) ((dashboard)getActivity()).selectedFromRetour(2);
        else ((dashboard)getActivity()).selectedFromRetour(1);
        if(dashboard.type!="Parent")
            progression=dashboard.eleve.getProgression();

        tvEspace_progression = view.findViewById(R.id.tvEspace_Progression);
        img_profile = view.findViewById(R.id.img_profile);
        retour_menu = view.findViewById(R.id.retour_menu);
        nbr_chapitres=view.findViewById(R.id.nbr_chapitres);
        nbr_matières=view.findViewById(R.id.nbr_matières);
        matières_lu=view.findViewById(R.id.matière_lu);

        nbr_chapitres.setText(progression.getChapitresLus().size()+"");
        adapterChapitreLu=new AdapterChapitreLu(getActivity(),progression.getChapitresLus());
        recycler_chapitres_lus=view.findViewById(R.id.recycler_chapitres_lus);
        recycler_chapitres_lus.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_chapitres_lus.setHasFixedSize(true);
        recycler_chapitres_lus.setAdapter(adapterChapitreLu);
        icone_progression = view.findViewById(R.id.icone_forum);

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




