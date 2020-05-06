package Model;

public class Eleve_parent extends Utilisateur {
    private long id_parent;
    private int id_niveau;
    private String lien_parenté;

    public Eleve_parent(){

    }
    public Eleve_parent(String nom, String prénom, String identifiant, String mot_de_passe,long id_parent,int id_niveau,String lien_parenté,String mail_parent,int rang_fils) {
        super(nom, prénom, identifiant, mot_de_passe,"parent"+rang_fils+"_"+mail_parent);
        this.id_parent=id_parent;
        this.id_niveau=id_niveau;
        this.lien_parenté=lien_parenté;
    }

    public long getId_parent() {
        return id_parent;
    }

    public void setId_parent(long id_parent) {
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
