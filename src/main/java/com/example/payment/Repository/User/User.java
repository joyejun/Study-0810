package com.example.payment.Repository.User;

import lombok.Getter;

@Getter
public class User {
    private static int USER_CURRNET_ID = 0;

    private static int idGenerator() {
        return ++USER_CURRNET_ID;
    }

    private Integer id;
    private String name;
    private boolean deleted = false;

    private User(Integer id, String name) {
        this.id = id;
        this.name = name;
        // this.deleted = deleted;
    }

    public static User create(String name) {
        int generatedId = idGenerator();
        return new User(generatedId, name);
    }
}
