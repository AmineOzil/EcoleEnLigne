package Model;

public class Utilisateur {

    private static int count = 0;
    private int id;
    public String nom;
    private String prénom;
    private String identifiant;
    private String mot_de_passe;

    public Utilisateur(String nom,String prénom,String identifiant,String mot_de_passe){
        this.id=++count;
        this.nom=nom;
        this.prénom=prénom;
        this.identifiant=identifiant;
        this.mot_de_passe=mot_de_passe;
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

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
