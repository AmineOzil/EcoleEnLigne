package com.devmobile.ecoleenligne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Chapitre;

public class AdapterChapitreLu extends RecyclerView.Adapter<AdapterChapitreLu.MyViewHolder> {

    Context c;
    ArrayList<String> chapitres;

    public AdapterChapitreLu(Context c, ArrayList<String> chapitres) {
        this.c = c;
        this.chapitres = chapitres;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chapitre_lu_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChapitreLu.MyViewHolder holder, final int position) {
        holder.nom.setText(chapitres.get(position));
        holder.img_chapitre.setText((position+1)+"");
    }


    @Override
    public int getItemCount() {
        return chapitres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView img_chapitre;
        TextView nom;
        //TextView description;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.img_chapitre = itemView.findViewById(R.id.img_chapitre);
            this.nom = itemView.findViewById(R.id.tvChapitre);
            //this.description = itemView.findViewById(R.id.tvDescription);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
