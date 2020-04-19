package Model;

import java.util.ArrayList;

public class Question {
private int id;
private String titre;
    private String contenu;
    private ArrayList<Réponse> réponses;


    public Question(int id,String titre,String contenu){
        this.id=id;
        this.titre=titre;
        this.contenu=contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public ArrayList<Réponse> getRéponses() {
        return réponses;
    }

    public void setRéponses(ArrayList<Réponse> réponses) {
        this.réponses = réponses;
    }
    public void ajouterReponse(Réponse réponse){
        réponses.add(réponse);
    }
}

