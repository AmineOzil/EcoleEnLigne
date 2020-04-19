package Model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Matière {
      private int id;
      private String nom;
      private Drawable image;
      private ArrayList<Chapitre> chapitres;

      public Matière(int id,String nom,Drawable image){
          this.setId(id);
          this.setNom(nom);
          image=image;
      }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public ArrayList<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(ArrayList<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }
    public void ajouterChapitre(Chapitre chapitre){
          chapitres.add(chapitre);
    }
}
