package org.example.model;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomFilter {

    private String topic;

    private Boolean isAnswered = false;

    public CustomFilter(){}

    public CustomFilter(String topic){this.topic = topic;}

    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public Boolean getAnswered() {
        return isAnswered;
    }

    public CustomFilter setAnswered(Boolean answered) {
        CustomFilter customFilter  = new CustomFilter();
        customFilter.isAnswered = answered;
        return customFilter;
    }
    public Feed filter(String topic,User user){
        Set<Question> filteredQuestions;
        if(topic==null){
            filteredQuestions = user.getFeed().getQuestions();
        }else{
            filteredQuestions = user.getFeed().getQuestions().stream().filter(question -> question.getTopics().contains(topic)).collect(Collectors.toSet());
        }
        if(this.isAnswered){
            filteredQuestions = filteredQuestions.stream().filter(question -> question.getResponses().size()>0).collect(Collectors.toSet());
        }
        return new Feed(filteredQuestions);
    }

}
