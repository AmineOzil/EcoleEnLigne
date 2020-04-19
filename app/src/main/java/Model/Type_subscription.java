package Model;

public class Type_subscription {
    private int id_type_subscription;
    private String type;
    private double prix;

    public Type_subscription(int id,String type,double prix){
        this.id_type_subscription=id;
        this.type=type;
        this.prix=prix;
    }

    public int getId_type_subscription() {
        return id_type_subscription;
    }

    public void setId_type_subscription(int id_type_subscription) {
        this.id_type_subscription = id_type_subscription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
