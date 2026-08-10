package com.example.payment.Repository.User;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private static final Map<Integer, User> USER = new HashMap<>();

    public List<User> findAll() {
        return USER.values().stream().toList();
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(USER.get(id));
    }

    public Optional<User> create(User entity) {
        int id = entity.getId();
        if (Objects.nonNull(USER.get(id))) {
            throw new RuntimeException();
        }
        User created = USER.put(id, entity);
        return Optional.ofNullable(created);
    }

    public void remove(Integer id) {
        if (Objects.nonNull(USER.get(id))) {
            throw new RuntimeException("");
        }
        USER.remove(id);
    }
}
