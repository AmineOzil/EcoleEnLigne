package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ProgressionMenu extends Fragment {
    //private Progression progression;
    private CardView cours_lus;
    private CardView nbr_conex_;
    private CardView quiz_scores;
    private ImageView retour;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.progression_menu,container,false);

        cours_lus=view.findViewById(R.id.cours_lus);
        nbr_conex_=view.findViewById(R.id.nbr_conex);
        quiz_scores=view.findViewById(R.id.quiz_scores);

        retour=view.findViewById(R.id.retour_accueil);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        nbr_conex_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ProgressionConnexion connexion =new ProgressionConnexion();
                connexion.setProgressionConnexion(connexion.getProgressionConnexion());
                ft.replace(R.id.contenu,connexion).addToBackStack(null);
                ft.commit();
            }
        });
 /*       cours_lus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                PdfView chapter =new PdfView();
                chapter.setUrl(chapitre.getContenu());
                ft.replace(R.id.contenu,chapter).addToBackStack(null);
                ft.commit();
            }
        });
        quiz_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                QuizView chapter =new QuizView();
                chapter.setQuiz(chapitre.getQuiz());
                ft.replace(R.id.contenu,chapter).addToBackStack(null);
                ft.commit();
            }
        });

     */


        return view;
    }
 /* public void setProgression(Progression progression){
        this.progression=progression;
    }
  */
}
