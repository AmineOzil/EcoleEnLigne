package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.FirebaseDatabase;

import Model.Eleve;

public class ScoreQuiz extends Fragment {
    private int score;
    private TextView scoret,msgQuiz;
    private ImageView reaction;
    private ImageView retour;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        updateScoreQuiz(dashboard.eleve,dashboard.type,score);
        View view=inflater.inflate(R.layout.resultat_quiz,container,false);
        ((dashboard)getActivity()).selectedFromRetour(2);
        scoret=view.findViewById(R.id.score);
        scoret.setText("Score: "+score+"/5");
        reaction=view.findViewById(R.id.reaction);
        msgQuiz=view.findViewById(R.id.msgQuiz);
        if(score<3){
            msgQuiz.setText("Révise le cours");
            reaction.setImageResource(R.drawable.triste);
        }
        retour=view.findViewById(R.id.retour_score1);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        return view;
    }
    public void setScore(int score){
        this.score=score;
    }
    public void updateScoreQuiz(final Eleve eleve, String type, int score){
        eleve.getProgression().ajouterScore(score);
        FirebaseDatabase.getInstance().getReference(type)
                .child(String.valueOf(eleve.getId())).setValue(eleve);
    }
}
