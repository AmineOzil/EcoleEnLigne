package Model;

public class Réponse {
    private int id;
    private String contenu;
    private int id_auteur;
    public Réponse(int id,String contenu,int id_auteur){
        this.id=id;
        this.id_auteur=id_auteur;
        this.contenu=contenu;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId_auteur() {
        return id_auteur;
    }

    public void setId_auteur(int id_auteur) {
        this.id_auteur = id_auteur;
    }
}
