package Model;

public class Payement {
    private int id;
    private String num_carte;
    private String CVV;
    private String type_carte;
    private String date_expiration;
    public Payement(int id,String num_carte,String CVV,String type_carte,String date_expiration){
        this.id=id;
        this.CVV=CVV;
        this.date_expiration=date_expiration;
        this.type_carte=type_carte;
        this.num_carte=num_carte;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getType_carte() {
        return type_carte;
    }

    public void setType_carte(String type_carte) {
        this.type_carte = type_carte;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }
}
