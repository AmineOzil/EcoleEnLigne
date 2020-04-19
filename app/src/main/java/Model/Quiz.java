package Model;

public class Quiz {
 private int id;
 private String question;
 private String choix1;
    private String choix2;
    private String choix3;
    private String choix4;

    public Quiz(int id,String question,String choix1,String choix2,String choix3,String choix4){
        this.id=id;
        this.question=question;
        this.choix1=choix1;
        this.setChoix2(choix2);
        this.choix3=choix3;
        this.choix4=choix4;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoix1() {
        return choix1;
    }

    public void setChoix1(String choix1) {
        this.choix1 = choix1;
    }

    public String getChoix2() {
        return choix2;
    }

    public void setChoix2(String choix2) {
        this.choix2 = choix2;
    }

    public String getChoix3() {
        return choix3;
    }

    public void setChoix3(String choix3) {
        this.choix3 = choix3;
    }

    public String getChoix4() {
        return choix4;
    }

    public void setChoix4(String choix4) {
        this.choix4 = choix4;
    }
}
