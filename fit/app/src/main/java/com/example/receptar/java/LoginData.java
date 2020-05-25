package com.example.receptar.java;

import lombok.Getter;
import lombok.Setter;

/**
 * public class which contains static info about logged user
 */
public class LoginData {

    @Setter
    @Getter
    private static User loggedUser;

    /**
     * @return id of logged user
     */
    public static int getLoggedUserId() {
        return loggedUser.getId();
    }

    /**
     * @return username of logged user
     */
    public static String getLoggedUserName() {
        return loggedUser.getUserName();
    }
}
