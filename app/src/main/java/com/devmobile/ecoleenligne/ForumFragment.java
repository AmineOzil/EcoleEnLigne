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

import Model.Questions_Forum;


public class ForumFragment extends Fragment {

        RecyclerView myRecycler;
        AdapterQuestion_Forum myAdapter;
        ArrayList<Questions_Forum> questionsData;
        DatabaseReference mDatabase;
        FirebaseRecyclerOptions<Questions_Forum> options ;
        TextView tvEspace_forum;
        ImageView icone_forum,img_profile;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.forum,container,false);

        tvEspace_forum = view.findViewById(R.id.tvEspace_Forum);
        img_profile = view.findViewById(R.id.img_profile);
        icone_forum = view.findViewById(R.id.icone_forum);


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


        myRecycler = view.findViewById(R.id.recycler_questions_forum);
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycler.setHasFixedSize(true);


        questionsData = new ArrayList<Questions_Forum>();
        mDatabase = FirebaseDatabase.getInstance().getReference("Forum").child("-M8BfQkCllGvxj6rwdXZ").child("questions");
        mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                Questions_Forum qst = dataSnapshot1.getValue(Questions_Forum.class);
                questionsData.add(qst);
                }
                myAdapter = new AdapterQuestion_Forum(getActivity(), questionsData);
                myRecycler.setAdapter(myAdapter);
                }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Oups.. something is wrong !", Toast.LENGTH_SHORT).show();
                }

                });

                return view;
        }

    public void getQuestionsCount(final Questions_Forum q) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Forum").child("-M8B_vlByh-H3vsyyI5g").child("questions");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                q.setId(q.getId()+(int)dataSnapshot.getChildrenCount());
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
