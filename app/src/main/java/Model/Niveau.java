package Model;

import java.util.ArrayList;

public class Niveau {
    private int id;
    private String nom;
    private ArrayList<Matière> matières;
    public Niveau(int id,String nom){
        this.id=id;
        this.nom=nom;
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

    public ArrayList<Matière> getMatières() {
        return matières;
    }

    public void setMatières(ArrayList<Matière> matières) {
        this.matières = matières;
    }
    public void ajouterMatière(Matière matiere){
        matières.add(matiere);
    }
}
