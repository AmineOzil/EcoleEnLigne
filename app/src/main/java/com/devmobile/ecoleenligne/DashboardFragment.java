package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dashboard_ts,container,false);
        switch (dashboard.niveau){
            case "Terminal L": view=inflater.inflate(R.layout.fragment_dashboard_tl,container,false);
                return view;
            case "Seconde":   view=inflater.inflate(R.layout.fragment_dashboard_s,container,false);
                return view;
            case "Primaire": view=inflater.inflate(R.layout.fragment_dashboard_p,container,false);
                return view;
            default: return view;

        }
    }

}
