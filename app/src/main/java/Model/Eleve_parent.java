package Model;

public class Eleve_parent extends Utilisateur {
    private int id_parent;
    private int id_niveau;
    private String lien_parenté;

    public Eleve_parent(int id, String nom, String prénom, String identifiant, String mot_de_passe,int id_parent,int id_niveau,String lien_parenté) {
        super(id, nom, prénom, identifiant, mot_de_passe);
        this.id_parent=id_parent;
        this.id_niveau=id_niveau;
        this.lien_parenté=lien_parenté;
    }

    public int getId_parent() {
        return id_parent;
    }

    public void setId_parent(int id_parent) {
        this.id_parent = id_parent;
    }

    public int getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(int id_niveau) {
        this.id_niveau = id_niveau;
    }

    public String getLien_parenté() {
        return lien_parenté;
    }

    public void setLien_parenté(String lien_parenté) {
        this.lien_parenté = lien_parenté;
    }
}
