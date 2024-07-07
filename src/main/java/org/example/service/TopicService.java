package org.example.service;

import org.example.exceptions.TopicException;
import org.example.model.Question;
import org.example.model.Topic;
import org.example.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TopicService {
    private final Map<String ,Topic> topics;
    private static TopicService instance = null;

    private TopicService(){
        this.topics = new HashMap<>();
    }
    synchronized public static TopicService getInstance(){
        if(instance==null){
            instance = new TopicService();
        }
        return instance;
    }

    public void addTopic(String name){
        Topic topic = new Topic(name);
        topics.put(name,topic);
    }

    //Assumption that topic exists
    //creates new topic when not found
    public void subscribe(User user,String topic){
        if(!topics.containsKey(topic)){
            addTopic(topic);
        }
        topics.get(topic).getUserList().add(user);
        updateFeed(user,topics.get(topic));
    }
    public void unSubscribe(User user,String topic){
        Topic topic1 = topics.get(topic);
        if(topic1.getUserList().contains(user)){
            topic1.getUserList().remove(user);
        }
        else throw new TopicException("Not a subscriber of this topic"); //throw Exception
    }

    //Assumption that topic exists
    //creates new topic when not found
    public void addQuestion(Question question,String topic){
        Topic topic1;
        if(!topics.containsKey(topic)){
            addTopic(topic);
        }
        topic1 = topics.get(topic);
        topic1.getQuestionList().add(question);
        updateFeed(question.getUser(),topic1);
    }
    public void updateFeed(User user,Topic topic){
        user.getFeed().getQuestions().addAll(topic.getQuestionList());
    }

    public boolean isUserSubscribed(String topic,User user){
        List<User> userList = topics.get(topic).getUserList();
        return userList.contains(user);
    }

}
