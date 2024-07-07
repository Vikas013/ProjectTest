package org.example;

import org.example.model.User;


public class Session {
    private static User loggedInUser=null;
    private Session(){};
    synchronized static public User getLoggedInUser() {
        return loggedInUser;
    }
    synchronized static public User setLoggedInUser(User user) {
        loggedInUser = user;
        return loggedInUser;
    }
}
