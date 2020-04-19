package Model;

import java.util.ArrayList;

public class Forum {
    private static Forum singleton_forum;
    private ArrayList<Question> questions;

    private Forum(){
        super();
    }
    public final static Forum getInstance() {
        if (Forum.singleton_forum == null) {
            synchronized(Forum.class) {
                if (Forum.singleton_forum == null) {
                    Forum.singleton_forum = new Forum();
                }
            }
        }
        return Forum.singleton_forum;
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
    public void addQuestion(Question question){
        this.questions.add(question);
    }
}
