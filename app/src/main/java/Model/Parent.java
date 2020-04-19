package Model;

public class Parent extends Utilisateur {
    private int number_fils;
    private String email;

    public Parent(int id, String nom, String prénom, String identifiant, String mot_de_passe,int number_fils,String email) {
        super(id, nom, prénom, identifiant, mot_de_passe);
        this.number_fils=number_fils;
        this.email=email;
    }


    public int getNumber_fils() {
        return number_fils;
    }

    public void setNumber_fils(int number_fils) {
        this.number_fils = number_fils;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
