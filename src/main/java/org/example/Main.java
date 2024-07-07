package org.example;

import org.example.model.CustomFilter;
import org.example.service.UserService;

import java.util.Arrays;

//Assumptions questions of topics will be shown in feed who are previously subscribed

public class Main {
    public static void main(String[] args) {
        UserService userService  = UserService.getInstance();
        userService.addUser("Vikas","Engineer");
        userService.subscribe("Java");
        userService.postQuestion("What is Java", Arrays.asList("Java","Engineering"));
        userService.postQuestion("What is OOPs?",Arrays.asList("Java"));

        userService.addResponse(1l,"Yes! Java is OOPS Language");
        userService.getFeed().getQuestions().stream().forEach(question -> System.out.println(question));
        userService.logout();

        userService.addUser("Dash","Manager");
        userService.subscribe("Engineering");
        userService.subscribe("Java");
        userService.postQuestion("Java is better than Python",Arrays.asList("Java"));
        userService.getFeed().getQuestions().stream().forEach(question -> System.out.println(question));
        userService.usSubscribe("Java");
        userService.getFeed().getQuestions().stream().forEach(question -> System.out.println(question));
        userService.logout();

        userService.login(1l);
        userService.postQuestion("Is java 21 released?", Arrays.asList("Java"));
        userService.logout();

        userService.login(2l);
        userService.getFeed().getQuestions().stream().forEach(question -> System.out.println(question));
        userService.addResponse(2l,"Java is vast");
        CustomFilter customFilter = new CustomFilter();
        customFilter.setAnswered(true);
        userService.getFeed(customFilter).getQuestions().stream().forEach(question -> System.out.println(question));


    }
}