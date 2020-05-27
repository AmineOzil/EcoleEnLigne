package Model;

public class Eleve extends Utilisateur {
    private int id_niveau;
    private Progression progression;
    public Eleve(){

    }
    public Eleve(String nom, String prénom, String identifiant, String mot_de_passe,int id_niveau,String email) {
        super(nom, prénom, identifiant, mot_de_passe,email);
        this.id_niveau=id_niveau;
        progression=new Progression();
    }

    public int getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(int id_niveau) {
        this.id_niveau = id_niveau;
    }

    public Progression getProgression() {
        if (progression==null) {setProgression(new Progression());}
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }
}
