package Model;

import java.util.ArrayList;

public class Chapitre {
    private int id;
    private int img;
    private String nom;
    private String contenu;
    private String description;
    private Quiz quiz;

    public Chapitre() {
    }

    public Chapitre(int id, int img, String nom, String contenu, String description, Quiz quiz) {
        this.id = id;
        this.img = img;
        this.nom = nom;
        this.contenu = contenu;
        this.description = description;
        this.quiz = quiz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quiz = quiz;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
