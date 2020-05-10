package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ScoreQuiz extends Fragment {
    private int score;
    private TextView scoret,msgQuiz;
    private ImageView reaction;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.resultat_quiz,container,false);
        scoret=view.findViewById(R.id.score);
        scoret.setText("Score: "+score+"/5");
        reaction=view.findViewById(R.id.reaction);
        msgQuiz=view.findViewById(R.id.msgQuiz);
        if(score<3){
            msgQuiz.setText("Révise le cours");
            reaction.setImageResource(R.drawable.triste);
        }
        return view;
    }
    public void setScore(int score){
        this.score=score;
    }
}
