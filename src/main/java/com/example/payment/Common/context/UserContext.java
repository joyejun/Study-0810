package com.example.payment.Common.context;

public class UserContext {
    private static final ThreadLocal<Integer> CURRENT_USER = new ThreadLocal<>();

    public static void setUserID(Integer userID) {
        CURRENT_USER.set(userID);
    }

    public static Integer getuserId() {
        return CURRENT_USER.get();
    }

    public static void clear() {
        CURRENT_USER.remove();
    }

//    public static AutoCloseable withUser(Integer userId) {
//        setUserID(userId);
//        return UserContext::clear;
//    }
    public static ContextScope withUser(Integer userId) {
        setUserID(userId);
        return new ContextScope();
    }


    public static class ContextScope implements AutoCloseable {
        @Override
        public void close() {
            clear();
        }
    }
}
