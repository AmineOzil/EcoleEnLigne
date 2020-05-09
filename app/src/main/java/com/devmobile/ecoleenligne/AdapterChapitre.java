package com.devmobile.ecoleenligne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Chapitre;

public class AdapterChapitre extends RecyclerView.Adapter<AdapterChapitre.MyViewHolder> {

    Context c;
    ArrayList<Chapitre> chapitres;

    public AdapterChapitre(Context c, ArrayList<Chapitre> chapitres) {
        this.c = c;
        this.chapitres = chapitres;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chapitre_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChapitre.MyViewHolder holder, int position) {
        holder.nom.setText(chapitres.get(position).getNom());
        holder.description.setText(chapitres.get(position).getDescription());
        holder.img_chapitre.setImageResource(R.drawable.chapter_img);
    }


    @Override
    public int getItemCount() {
        return chapitres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_chapitre;
        TextView nom,description;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.img_chapitre = itemView.findViewById(R.id.img_chapitre);
            this.nom = itemView.findViewById(R.id.tvChapitre);
            this.description = itemView.findViewById(R.id.tvDescription);

        }
    }
}
