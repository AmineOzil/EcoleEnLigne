package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    AdapterChapitre myAdapter;
    ArrayList<Chapitre> chapterData ;
    DatabaseReference mDatabase;
    FirebaseRecyclerOptions<Chapitre> options ;
    TextView retour_matières2;
    ImageView retour_matières1, img_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cour_physique,container,false);

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
                ft.replace(R.id.contenu,profile);
                ft.commit();
            }
        });



        myRecycler = view.findViewById(R.id.recycler_chapitres);
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycler.setHasFixedSize(true);


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




    /*

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Matièree").child("Chapitre");

        options = new FirebaseRecyclerOptions.Builder<Chapitre>()
                .setQuery(mDatabase,Chapitre.class).build();

        adapter = new FirebaseRecyclerAdapter<Chapitre, HolderChapitre>(options) {
            @Override
            protected void onBindViewHolder(@NonNull HolderChapitre holder, int position, @NonNull Chapitre model) {
                holder.nom.setText(model.getNom());
                holder.description.setText(model.getDescription());
                holder.img_chapitre.setImageResource(model.getImg());
            }

            @NonNull
            @Override
            public HolderChapitre onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapitre_row,parent,false);
                return new HolderChapitre(view);
            }
        };

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter.startListening();
        myRecycler.setAdapter(adapter);
    }

    */

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Chapitre,ChapitreViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Chapitre, ChapitreViewHolder>
                (null) {
            @Override
            public ChapitreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            protected void onBindViewHolder(@NonNull ChapitreViewHolder holder, int position, @NonNull Chapitre model) {
                holder.setTitle(model.getNom());
                holder.setDescription(model.getDescription());
            }
        };
        myRecycler.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ChapitreViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public ChapitreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mView = itemView;
        }
        public void setTitle(String title)
        {
            TextView post_Title = (TextView)mView.findViewById(R.id.tvChapitre);
            post_Title.setText(title);
        }
        public void setDescription(String description)
        {
            TextView post_Description = (TextView)mView.findViewById(R.id.tvDescription);
            post_Description.setText(description);
        }
    }*/

    /*  private ArrayList<Chapitre> getMyList(){

        ArrayList<Chapitre> chapitres = new ArrayList<>();
        Chapitre ch1 = new Chapitre();
        ch1.setNom("Chapitre 1");
        ch1.setContenu("Ondes et matière");
        ch1.setImg(R.drawable.ellipse);

        chapitres.add(ch1);

        Chapitre ch2 = new Chapitre();
        ch2.setNom("Chapitre 2");
        ch2.setContenu("Temps, Mouvement et évolution");
        ch2.setImg(R.drawable.ellipse);

        chapitres.add(ch2);

        Chapitre ch3 = new Chapitre();
        ch3.setNom("Chapitre 3");
        ch3.setContenu("Structure et transformation de la matière");
        ch3.setImg(R.drawable.ellipse);

        chapitres.add(ch3);

        Chapitre ch4 = new Chapitre();
        ch4.setNom("Chapitre 4");
        ch4.setContenu("Energie, matière et rayonnement");
        ch4.setImg(R.drawable.ellipse);

        chapitres.add(ch4);

        return chapitres;
    }*/
        return view;
    }
}
