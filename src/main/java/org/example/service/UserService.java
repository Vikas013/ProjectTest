package org.example.service;

import org.example.Session;
import org.example.exceptions.NoTopicsAttachedException;
import org.example.exceptions.UserNotSubscribedException;
import org.example.model.CustomFilter;
import org.example.model.Feed;
import org.example.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<Long,User> userMap;
    private static UserService instance = null;
    private UserService(){
        userMap = new HashMap<>();
    }
    synchronized public static UserService getInstance(){
        if(instance==null){
            instance = new UserService();
        }
        return instance;
    }
    public void addUser(String name,String profession){
        if(Session.getLoggedInUser()==null){
            User user = new User(name,profession,System.currentTimeMillis());
            userMap.put(user.getId(),user);
            System.out.println("Account Created Successfully");
            Session.setLoggedInUser(user);
            System.out.println("Logged in Successfully"+" "+user.getName());
            System.out.println("____________");
        }
        else System.out.println("Already user logged in");
    }
    public void login(Long userId){
        if(Session.getLoggedInUser()==null){
            if(!userMap.containsKey(userId)){
                System.out.println("No user exists with this userId");
                return;
            }
            Session.setLoggedInUser(userMap.get(userId));
            System.out.println("Logged in Successfully"+" "+userMap.get(userId).getName());
            System.out.println("____________");
        }
        else System.out.println("Already user logged in");
    }
    public void logout(){
        if(Session.getLoggedInUser()!=null){
            Session.setLoggedInUser(null);
            System.out.println("Logged Out Successfully ");
            System.out.println("____________");
        }
        else System.out.println("No user logged in ");
    }
    public void postQuestion(String question, List<String> topics){
        try{
            System.out.println("You have posted question successfully ");
            QuestionService.getInstance().addQuestion(question,topics,Session.getLoggedInUser());
        }
        catch (NoTopicsAttachedException e){
            System.out.println("Attach atleast one topic to post question ");
        }

    }
    public void addResponse(Long questionId,String response){
        try {
            QuestionService.getInstance().addResponse(questionId,response,Session.getLoggedInUser());
            System.out.println("You have posted response successfully ");
        }
        catch (UserNotSubscribedException e){
            System.out.println("You are not subscribed to topic of question ");
        }

    }
    public void subscribe(String topic){
        System.out.println("You are subscribed to "+topic);
        TopicService.getInstance().subscribe(Session.getLoggedInUser(),topic);
    }
    public void usSubscribe(String topic){
        System.out.println("You are unsubscribed to "+topic);
        TopicService.getInstance().unSubscribe(Session.getLoggedInUser(),topic);
    }

    public Feed getFeed(){
        System.out.println("getting your feed....");
        return Session.getLoggedInUser().getFeed();
    }

    public Feed getFeed(CustomFilter filter){
        System.out.println("getting your feed....");
        return filter.filter(filter.getTopic(),Session.getLoggedInUser());
    }

}