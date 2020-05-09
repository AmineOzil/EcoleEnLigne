package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DashboardFragment extends Fragment {
    CardView maths;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dashboard_ts,container,false);
        if (dashboard.niveau=="Terminal S"){
        maths=view.findViewById(R.id.mathsCard);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment chapitre=new ChapitresMatiere();
                ft.replace(R.id.contenu,chapitre);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        }
        switch (dashboard.niveau){
            case "Terminal L": view=inflater.inflate(R.layout.fragment_dashboard_tl,container,false);
            case "Seconde":   view=inflater.inflate(R.layout.fragment_dashboard_s,container,false);
            case "Primaire": view=inflater.inflate(R.layout.fragment_dashboard_p,container,false);
        }
        return view;
    }

}
