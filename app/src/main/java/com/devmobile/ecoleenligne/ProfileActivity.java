package com.devmobile.ecoleenligne;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class ProfileActivity extends Fragment {
    Button deconnexion;
    ImageView retour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_profile,container,false);
        deconnexion= view.findViewById(R.id.bt_deconnexion);
        retour= view.findViewById(R.id.retour_profile);
        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deconnect = new Intent(getActivity(),login.class);
                startActivity(deconnect);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getActivity(), dashboard.class);
                startActivity(back);
            }
        });
        return view;
    }
}