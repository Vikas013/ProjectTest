package org.example.model;
import lombok.Data;
import java.util.List;

@Data
public class Question {
    private static Long currentId = 0L;
    private final Long id;
    private String question;
    private List<String> responses;
    private User user;
    private List<String> topics;
    private Long timeStamp;

    private Integer votes;

    public Question(String question, List<String> responses, User user, Long timeStamp, List<String> topics) {
        this.id = generateId();
        this.question = question;
        this.responses = responses;
        this.user = user;
        this.timeStamp = timeStamp;
        this.topics = topics;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
    private static synchronized Long generateId() {
        currentId++;
        return currentId;
    }
    public static Long getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(Long currentId) {
        Question.currentId = currentId;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString(){

        return "_________\n"+"Question:"+this.getId()+" "+ this.getQuestion()+"\n"+
                "Posted by:"+ this.getUser().getName()+"\n"+
                "Responses:"+this.getResponses().toString()+"\n"+
                "Topics:"+this.getTopics().toString()+"\n"+
                "timeStamp:"+this.getTimeStamp()+"\n"
                ;
    }
}
