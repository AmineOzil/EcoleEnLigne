package com.devmobile.ecoleenligne;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import Model.Questions_Forum;
import Model.Réponses_Forum;


public class ForumFragment extends Fragment {

        RecyclerView myRecycler;
        AdapterQuestion_Forum myAdapter;
        ArrayList<Questions_Forum> questionsData;
        DatabaseReference mDatabase;
        FirebaseRecyclerOptions<Questions_Forum> options ;
        TextView tvEspace_forum;
        ImageView icone_forum,img_profile;
        FloatingActionButton add_qst;
        ScrollView scrollView;


    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.forum,container,false);
    ((dashboard)getActivity()).selectedFromRetour(3);
    tvEspace_forum = view.findViewById(R.id.tvEspace_Forum);
        img_profile = view.findViewById(R.id.img_profile);
        icone_forum = view.findViewById(R.id.icone_forum);
        add_qst = view.findViewById(R.id.add_qst);
        scrollView = (ScrollView)view.findViewById(R.id.scrollViewQuestions);

        //add_qst.attach


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

        add_qst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
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
                ArrayList<Questions_Forum> qsts = new ArrayList<>();
            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                Questions_Forum qst = dataSnapshot1.getValue(Questions_Forum.class);
                    qsts.add(qst);
                }
            setquestionsData(qsts);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Forum").child("-M8BfQkCllGvxj6rwdXZ").child("questions");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //ArrayList<Réponses_Forum> qsts =new ArrayList<>();
                q.setId(q.getId()+(int)dataSnapshot.getChildrenCount());
                FirebaseDatabase.getInstance().getReference("Forum").child("-M8BfQkCllGvxj6rwdXZ").child("questions").child(q.getId()+"").setValue(q);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Formulaire de questions");
        dialog.setMessage("Fournissez-nous vos précieuses questions");

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.fragment_add_question, null);

        final MaterialEditText edTitreQuestion = v.findViewById(R.id.edTitreQuestion);
        final MaterialEditText edContenuQuestion = v.findViewById(R.id.edContenuQuestion);
        final ArrayList<Réponses_Forum> reps = new ArrayList<Réponses_Forum>();

        dialog.setView(v);

        dialog.setPositiveButton("Envoyer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(edTitreQuestion.getText().toString())){
                    Toast.makeText(getActivity(),"Veuillez introduire le titre de votre questions SVP..",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(edContenuQuestion.getText().toString())){
                    Toast.makeText(getActivity(),"Veuillez saisir votre questions SVP..",Toast.LENGTH_SHORT).show();
                }
                Questions_Forum q = new Questions_Forum(edTitreQuestion.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail(),edContenuQuestion.getText().toString(),reps);
                getQuestionsCount(q);
                //edTitreQuestion.setText("");
                //edContenuQuestion.setText("");
                //edTitreQuestion.requestFocus();
            }
        });

        dialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setquestionsData(ArrayList<Questions_Forum> questions){
        questionsData = questions;
    }

}
