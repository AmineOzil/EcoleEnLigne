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

import Model.Chapitre;

public class ChapitreMenu extends Fragment {
    private Chapitre chapitre;
    private CardView cours;
    private CardView vidéo;
    private CardView quiz;
    private ImageView retour;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.chapitre_fragment,container,false);
        ((dashboard)getActivity()).selectedFromRetour(2);
        cours=view.findViewById(R.id.cours);
        vidéo=view.findViewById(R.id.video);
        retour=view.findViewById(R.id.retour_chapitre);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        quiz=view.findViewById(R.id.quiz);
        cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                PdfView chapter =new PdfView();
                chapter.setUrl(chapitre.getContenu());
                chapter.setChapitreNom(chapitre.getNom());
                ft.replace(R.id.contenu,chapter).addToBackStack(null);
                ft.commit();
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
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
        vidéo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                VideoCours videoCours =new VideoCours();
                videoCours.setVideo(videoCours.getVideo());
                ft.replace(R.id.contenu, videoCours).addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
    public void setChapitre(Chapitre chapitre){
        this.chapitre=chapitre;
    }

}
