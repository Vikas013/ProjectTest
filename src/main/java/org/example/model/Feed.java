package org.example.model;


import java.util.Set;

public class Feed {
    private Set<Question> questions;
    public Set<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    public Feed(Set<Question> questions) {
        this.questions = questions;
    }

}
