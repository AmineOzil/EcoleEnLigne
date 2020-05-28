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

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import Model.Progression;

public class ProgressionConnexion extends Fragment {


    TextView tvEspace_progression;
    ImageView icone_progression,img_profile, retour_menu;
    Progression progression;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_progression,container,false);
        if(dashboard.niveau.matches("Parent")) ((dashboard)getActivity()).selectedFromRetour(2);
        else ((dashboard)getActivity()).selectedFromRetour(1);
        if(dashboard.type!="Parent")
        progression=dashboard.eleve.getProgression();
        tvEspace_progression = view.findViewById(R.id.tvEspace_Progression);
        img_profile = view.findViewById(R.id.img_profile);
        retour_menu = view.findViewById(R.id.retour_menu);

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

        GraphView graph = (GraphView) view.findViewById(R.id.graph_progression);
        ArrayList<DataPoint> result_quiz=new ArrayList<>();
        result_quiz.add(new DataPoint(0,0));
        int i=1;
        for(int j:progression.getQuizScores()){
            result_quiz.add(new DataPoint(i,j));
            i++;
        }
        DataPoint[] datas=new DataPoint[result_quiz.size()];
        datas=result_quiz.toArray(datas);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(datas);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    if(value!=0)
                    return  super.formatLabel(value, isValueX);
                    else return "essai";

                } else {
                    if(value!=0)
                        return  super.formatLabel(value, isValueX);
                    else return "score";
                }
            }
        });

        // Coloring the background part under the line
        series.setDrawBackground(true);
        // Visualiser les points
        series.setDrawDataPoints(true);

        // Enable zooming and scrolling in the graph
        //graph.getViewport().setScalable(true);
        //graph.getViewport().setScalable(true);


        graph.addSeries(series);


        return view;
    }

    public void setProgression(Progression c){
        this.progression=c;
    }

}




