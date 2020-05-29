package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import Model.Progression;

public class ProgressionMenu extends Fragment {
    //private Progression progression;
    private CardView cours_lus;
    private CardView nbr_conex_;
    private CardView quiz_scores;
    private ImageView retour_accueil;
    public Progression progression;
    private TextView retourtxt;
    private ImageView retour;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.progression_menu,container,false);
        retourtxt=view.findViewById(R.id.retourtxt);
        retour=view.findViewById(R.id.retour_menu);
        if(dashboard.niveau.matches("Parent")){ ((dashboard)getActivity()).selectedFromRetour(2);
        retourtxt.setVisibility(View.VISIBLE);
        retour.setVisibility(View.VISIBLE);
        }
        else ((dashboard)getActivity()).selectedFromRetour(1);
        cours_lus=view.findViewById(R.id.cours_lus);
        nbr_conex_=view.findViewById(R.id.nbr_conex);
        quiz_scores=view.findViewById(R.id.quiz_scores);
        quiz_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ProgressionQuiz connexion =new ProgressionQuiz();
                if(dashboard.niveau.matches("Parent"))
                connexion.setProgression(progression);
                ft.replace(R.id.contenu,connexion).addToBackStack(null);
                ft.commit();
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
       cours_lus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ProgressionLecture lecture =new ProgressionLecture();
                if(dashboard.niveau.matches("Parent"))
                    lecture.setProgression(progression);
                ft.replace(R.id.contenu,lecture).addToBackStack(null);
                ft.commit();
            }
        });
        nbr_conex_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ProgressionConnexion connexion =new ProgressionConnexion();
                if(dashboard.niveau.matches("Parent"))
                    connexion.setProgression(progression);
                ft.replace(R.id.contenu,connexion).addToBackStack(null);
                ft.commit();
            }
        });




        return view;
    }
  public void setProgression(Progression progression){
        this.progression=progression;
    }
    public Progression getProgression(){
       return this.progression;
    }
}
