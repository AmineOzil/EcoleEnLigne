package Model;

import java.util.ArrayList;

public class Quiz {

    private int id;
    private ArrayList<Question_Quiz> questions;

    public Quiz(){}
    public Quiz(int id, ArrayList<Question_Quiz> questions) {
        this.id = id;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Question_Quiz> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question_Quiz> questions) {
        this.questions = questions;
    }


}
