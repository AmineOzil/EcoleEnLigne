package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Chapitre;

public class ChapitresMatiere extends Fragment {

    RecyclerView myRecycler;
    ScrollView mScroll;
    AdapterChapitre myAdapter;
    ArrayList<Chapitre> chapterData ;
    DatabaseReference mDatabase;
    FirebaseRecyclerOptions<Chapitre> options ;
    TextView retour_matières2;
    ImageView retour_matières1, img_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cour_maths,container,false);
        ((dashboard)getActivity()).selectedFromRetour(2);
        retour_matières1 = view.findViewById(R.id.retour_matieres);
        retour_matières2 = view.findViewById(R.id.tvRetour_matieres);
        img_profile = view.findViewById(R.id.img_profile);



        retour_matières1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment dash =new DashboardFragment();
                ft.replace(R.id.contenu,dash);
                ft.commit();
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



        myRecycler = view.findViewById(R.id.recycler_chapitres);
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycler.setHasFixedSize(true);
        myRecycler.bringToFront();
        mScroll=view.findViewById(R.id.scrollingchapitres);
        mScroll.bringToFront();
        chapterData = new ArrayList<Chapitre>();
        mDatabase = FirebaseDatabase.getInstance().getReference("Matière").child("-M6ojokYtf2FoVlFlr-H").child("chapitres");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Chapitre chapitre = dataSnapshot1.getValue(Chapitre.class);
                    chapterData.add(chapitre);
                }
                myAdapter = new AdapterChapitre(getActivity(), chapterData);
                myRecycler.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Oups.. something is wrong !", Toast.LENGTH_SHORT).show();
            }


        });

        return view;
    }
}
