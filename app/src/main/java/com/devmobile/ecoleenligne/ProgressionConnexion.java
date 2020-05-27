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
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import Model.Questions_Forum;

public class ProgressionConnexion extends Fragment {

    RecyclerView myRecycler;
    AdapterQuestion_Forum myAdapter;
    DatabaseReference mDatabase;
    FirebaseRecyclerOptions<Questions_Forum> options ;
    TextView tvEspace_forum;
    ImageView icone_progression,img_profile, retour_menu;
    FloatingActionButton add_qst;
    ProgressionConnexion connexion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_progression,container,false);

        tvEspace_forum = view.findViewById(R.id.tvEspace_Progression);
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
                ft.replace(R.id.contenu,profile);
                ft.commit();
            }
        });

        GraphView graph = (GraphView) view.findViewById(R.id.graph_progression);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return "chap " + super.formatLabel(value, isValueX);

                } else {
                    return super.formatLabel(value, isValueX);
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

    public void setProgressionConnexion(ProgressionConnexion c){
        this.connexion=c;
    }
    public ProgressionConnexion getProgressionConnexion(){
        return connexion;
    }
}




