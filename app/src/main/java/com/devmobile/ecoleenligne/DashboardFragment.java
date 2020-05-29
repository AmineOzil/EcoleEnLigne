package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Eleve_parent;

public class DashboardFragment extends Fragment {
    CardView maths;
    CardView fils1;
    CardView fils2;
    CardView fils3;
    CardView fils4;
    TextView fils1t;
    TextView fils2t;
    TextView fils3t;
    TextView fils4t;
    ArrayList<CardView> listfils=new ArrayList();
    ArrayList<TextView> listfilst=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dashboard_ts,container,false);
        Log.v("Niveau ",dashboard.niveau);
        ((dashboard)getActivity()).selectedFromRetour(2);
        switch (dashboard.niveau){
            case "Terminal L": view=inflater.inflate(R.layout.fragment_dashboard_tl,container,false); break;
            case "Seconde":   view=inflater.inflate(R.layout.fragment_dashboard_s,container,false); break;
            case "Primaire": view=inflater.inflate(R.layout.fragment_dashboard_p,container,false); break;
            case "Parent": view=inflater.inflate(R.layout.fragment_dashboard_parent,container,false); break;
        }
        if (dashboard.niveau=="Terminal S"){
            maths=view.findViewById(R.id.mathsCard);
            maths.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm=getFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    Fragment chapitre=new ChapitresMatiere();
                    ft.replace(R.id.contenu,chapitre);
                    ft.commit();
                }
            });
        }else if (dashboard.niveau=="Parent"){
            view=inflater.inflate(R.layout.fragment_dashboard_parent,container,false);
            fils1=view.findViewById(R.id.fils1card);
            fils1t=view.findViewById(R.id.fils1);
            fils2=view.findViewById(R.id.fils2card);
            fils2t=view.findViewById(R.id.fils2);
            fils3=view.findViewById(R.id.fils3card);
            fils3t=view.findViewById(R.id.fils3);
            fils4=view.findViewById(R.id.fils4card);
            fils4t=view.findViewById(R.id.fils4);
            listfils=new ArrayList(Arrays.asList(fils1,fils2,fils3,fils4));
            listfilst=new ArrayList<>(Arrays.asList(fils1t,fils2t,fils3t,fils4t));
            int i=0;
            Log.v("nombre des fils",dashboard.listefils.size()+"");
            for (Eleve_parent e: dashboard.listefils){
                listfils.get(i).setVisibility(View.VISIBLE);
                Log.v("Prénoms",e.getPrénom());
                listfilst.get(i).setText(e.getPrénom());
                i++;
            }
            fils1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm=getFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ProgressionMenu progressionMenu =new ProgressionMenu();
                    progressionMenu.setProgression(dashboard.listefils.get(0).getProgression());
                    ft.replace(R.id.contenu,progressionMenu).addToBackStack(null);
                    ft.commit();
                }
            });
            fils2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm=getFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ProgressionMenu progressionMenu =new ProgressionMenu();
                    progressionMenu.setProgression(dashboard.listefils.get(1).getProgression());
                    ft.replace(R.id.contenu,progressionMenu).addToBackStack(null);
                    ft.commit();
                }
            });
            fils3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm=getFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ProgressionMenu progressionMenu =new ProgressionMenu();
                    progressionMenu.setProgression(dashboard.listefils.get(2).getProgression());
                    ft.replace(R.id.contenu,progressionMenu).addToBackStack(null);
                    ft.commit();
                }
            });
            fils4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm=getFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ProgressionMenu progressionMenu =new ProgressionMenu();
                    progressionMenu.setProgression(dashboard.listefils.get(3).getProgression());
                    ft.replace(R.id.contenu,progressionMenu).addToBackStack(null);
                    ft.commit();
                }
            });
        }

        return view;
    }



}
