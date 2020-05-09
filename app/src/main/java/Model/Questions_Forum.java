package Model;

import java.util.ArrayList;

public class Questions_Forum {
    private int id;
    private String titre;
    private String contenu;
    private ArrayList<Réponses_Forum> réponses;


    public Questions_Forum(int id, String titre, String contenu){
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

    public ArrayList<Réponses_Forum> getRéponses() {
        return réponses;
    }

    public void setRéponses(ArrayList<Réponses_Forum> réponses) {
        this.réponses = réponses;
    }
    public void ajouterReponse(Réponses_Forum réponse){
        réponses.add(réponse);
    }
}

