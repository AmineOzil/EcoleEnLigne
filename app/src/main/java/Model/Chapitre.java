package Model;

import java.util.ArrayList;

public class Chapitre {
    private int id;
    private String nom;
    private String contenu;
    private ArrayList<Quiz> quizzes;

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

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
    public void ajouterQuiz(Quiz quiz){
        quizzes.add(quiz);
    }
}
