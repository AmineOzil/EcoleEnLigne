package com.devmobile.ecoleenligne;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Collections;

import Model.Question_Quiz;
import Model.Quiz;

public class QuizView extends Fragment {
    ImageView retour;
    private TextView questionView;
    private TextView choix1,choix2,choix3,choix4;
    LinearLayout questionetchoix;
    private Quiz quiz;
    private int compteurQst,compteurCorrect;
    View previous;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Collections.shuffle(quiz.getQuestions());
        compteurQst=1;
        compteurCorrect=0;
        View view=inflater.inflate(R.layout.fragment_quiz,container,false);
        retour=view.findViewById(R.id.retour_matieres1);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        questionView=view.findViewById(R.id.question);
        questionView.setText("Question"+compteurQst+"/5: "+quiz.getQuestions().get(0).getContenu());
        questionetchoix=view.findViewById(R.id.questionchoix);
        choix1=view.findViewById(R.id.choix1);
        choix1.setText(quiz.getQuestions().get(0).getChoix1());
        choix2=view.findViewById(R.id.choix2);
        choix2.setText(quiz.getQuestions().get(0).getChoix2());
        choix3=view.findViewById(R.id.choix3);
        choix3.setText(quiz.getQuestions().get(0).getChoix3());
        choix4=view.findViewById(R.id.choix4);
        choix4.setText(quiz.getQuestions().get(0).getChoix4());
        choix1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aRépondu(choix1,quiz.getQuestions().get(compteurQst-1).getRéponse(),questionetchoix);
            }
        });
        choix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aRépondu(choix2,quiz.getQuestions().get(compteurQst-1).getRéponse(),questionetchoix);
            }
        });
        choix3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aRépondu(choix3,quiz.getQuestions().get(compteurQst-1).getRéponse(),questionetchoix);
            }
        });
        choix4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aRépondu(choix4,quiz.getQuestions().get(compteurQst-1).getRéponse(),questionetchoix);
            }
        });
        return view;
    }
    public void setQuiz(Quiz quiz){
        this.quiz=quiz;
    }
    public void setPrevious(View v){
        previous=v;
    }
    public void aRépondu(TextView tv, String réponse, LinearLayout questionetchoix){
        if(tv.getText().toString().trim().matches(réponse)) {
            tv.setBackgroundResource(R.drawable.choixcorrect);
            compteurCorrect++;
        }
        else {
            tv.setBackgroundResource(R.drawable.choixfaux);
            for (int i=1;i<=5;i++){
                if(((TextView)questionetchoix.getChildAt(i)).getText().toString().matches(réponse)){
                    ((TextView)questionetchoix.getChildAt(i)).setBackgroundResource(R.drawable.choixcorrect);
                    break;
                }
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 3s = 3000ms
                if (compteurQst<+5)
                passerQuestion(quiz.getQuestions().get(compteurQst));
                else{
                    questionView.setText("Vous avez terminé le quiz votre score est: "+compteurCorrect+"/5");
                }
            }
        }, 3000);
    }
    public void passerQuestion(Question_Quiz question_quiz){
        compteurQst++;
        questionView.setText("Question"+compteurQst+"/5: "+question_quiz.getContenu());
        choix1.setBackgroundResource(R.drawable.round_edit_text);
        choix2.setBackgroundResource(R.drawable.round_edit_text);
        choix3.setBackgroundResource(R.drawable.round_edit_text);
        choix4.setBackgroundResource(R.drawable.round_edit_text);
        choix1.setText(question_quiz.getChoix1());
        choix2.setText(question_quiz.getChoix2());
        choix3.setText(question_quiz.getChoix3());
        choix4.setText(question_quiz.getChoix4());
    }

}