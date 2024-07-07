package org.example.model;

import lombok.Data;

import java.util.HashSet;

@Data
public class User {
    private static Long currentId = 0L;
    private final Long id;
    private String name;
    private String profession;
    private long timeStamp;
    private Feed feed;
    public User(String name,String profession,Long timeStamp)
    {
        this.id = generateId();
        this.name = name;
        this.profession = profession;
        this.timeStamp = timeStamp;
        this.feed = new Feed(new HashSet<>());
    }
    private static synchronized Long generateId() {
        currentId++;
        return currentId;
    }
    public static Long getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(Long currentId) {
        User.currentId = currentId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Feed getFeed() {

        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}


