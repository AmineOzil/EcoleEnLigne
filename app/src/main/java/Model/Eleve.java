package Model;

public class Eleve extends Utilisateur {
    private int id_niveau;
    private String email;

    public Eleve(int id, String nom, String prénom, String identifiant, String mot_de_passe,int id_niveau,String email) {
        super(id, nom, prénom, identifiant, mot_de_passe);
        this.id_niveau=id_niveau;
        this.email=email;
    }

    public int getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(int id_niveau) {
        this.id_niveau = id_niveau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
