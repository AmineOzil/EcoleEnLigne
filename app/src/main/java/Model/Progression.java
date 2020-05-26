package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Progression implements Serializable {
    private ArrayList<String> HistoriqueConnexion=new ArrayList<>();
    private ArrayList<Integer> QuizScores=new ArrayList<>();
    private ArrayList<String> ChapitresLus=new ArrayList<>();
    public Progression(){
        setHistoriqueConnexion(new ArrayList<String>());
        setQuizScores(new ArrayList<Integer>());
        setChapitresLus(new ArrayList<String>());
    }

    public ArrayList<String> getHistoriqueConnexion() {
        return HistoriqueConnexion;
    }

    public void setHistoriqueConnexion(ArrayList<String> historiqueConnexion) {
        HistoriqueConnexion = historiqueConnexion;
    }
    public void ajouterConnexion(String d){
        HistoriqueConnexion.add(d);
    }

    public ArrayList<Integer> getQuizScores() {

        return QuizScores;
    }

    public void setQuizScores(ArrayList<Integer> quizScores) {
        QuizScores = quizScores;
    }
    public void ajouterScore(int score){
        QuizScores.add(score);
    }
    public ArrayList<String> getChapitresLus() {
        return ChapitresLus;
    }

    public void setChapitresLus(ArrayList<String> chapitresLus) {
        ChapitresLus = chapitresLus;
    }
    public void ajouterChapitre(String chapitre){
        ChapitresLus.add(chapitre);
    }
}
