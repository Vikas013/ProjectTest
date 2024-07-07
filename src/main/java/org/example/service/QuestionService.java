package org.example.service;

import org.example.exceptions.NoTopicsAttachedException;
import org.example.exceptions.UserNotSubscribedException;
import org.example.model.Question;
import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionService {
    private final Map<Long, Question> questions;
    private final TopicService topicService = TopicService.getInstance();
    private static QuestionService instance=null;

    synchronized public static QuestionService getInstance(){
        if(instance==null){
            instance = new QuestionService();
        }
        return instance;
    }
    private QuestionService(){
        this.questions = new HashMap<>();

    }
    public void addQuestion(String question, List<String> topics, User user) throws NoTopicsAttachedException {
        if(topics.size()==0) throw new NoTopicsAttachedException("No topic attached to question");
        Question newQuestion = new Question(question,new ArrayList<>(),user,System.currentTimeMillis(),topics);
        questions.put(newQuestion.getId(),newQuestion);
        topics.stream().forEach(topic -> topicService.addQuestion(newQuestion,topic));
    }
    public void addResponse(Long questionId,String response,User user) throws UserNotSubscribedException{
        if(!checkUserSubscribedToQuestionTopics(questionId,user)) throw new UserNotSubscribedException("User not Subscribed to topic of question");
        questions.get(questionId).getResponses().add(response);
    }

    public boolean checkUserSubscribedToQuestionTopics(Long questionId, User user){
        List<String> topics = questions.get(questionId).getTopics();
        boolean isSubscribed = false;
        for(String topic:topics){
            if(topicService.isUserSubscribed(topic,user)) isSubscribed=true;
        }
        return isSubscribed;
    }

    public void upVote(Long questionId,User user)  throws UserNotSubscribedException{
        if(!checkUserSubscribedToQuestionTopics(questionId,user)) throw new UserNotSubscribedException("User not Subscribed to topic of question");
        Question q = questions.get(questionId);
        int votes = q.getVotes();
        votes+=1;
        q.setVotes(votes);
    }
}
