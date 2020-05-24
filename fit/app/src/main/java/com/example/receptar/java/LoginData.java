package com.example.receptar.java;

import lombok.Getter;
import lombok.Setter;

public class LoginData {

    @Setter
    @Getter
    private static User loggedUser;

    public static int getLoggedUserId() {
        return loggedUser.getId();
    }

    public static String getLoggedUserName() {
        return loggedUser.getUserName();
    }
}
