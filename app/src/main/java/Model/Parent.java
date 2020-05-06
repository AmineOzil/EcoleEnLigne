package Model;

public class Parent extends Utilisateur {
    private int number_fils;
    public Parent(){

    }
    public Parent(String nom, String prénom, String identifiant, String mot_de_passe,int number_fils,String email) {
        super(nom, prénom, identifiant, mot_de_passe,email);
        this.number_fils=number_fils;
    }


    public int getNumber_fils() {
        return number_fils;
    }

    public void setNumber_fils(int number_fils) {
        this.number_fils = number_fils;
    }


}
