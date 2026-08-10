package com.example.payment.Repository.user;

import com.example.payment.Repository.IRepository;
import com.example.payment.Repository.Payment.Payment;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.*;


@Repository
public class UserRepository implements IRepository<Integer, User> {
    private final static Map<Integer, User> USER = new HashMap<>();

    @Override
    public List<User> findAll() {
        return USER.values().stream().toList();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(USER.get(id));
    }

    @Override
    public Optional<User> create(User entity) {
        int id = entity.getId();
        if (Objects.nonNull(id)) {
            throw new RuntimeException("기존에 해당하는 아이디가 이미 존재합니다." + id);
        }
        User created = USER.put(id, entity);
        return Optional.ofNullable(created);
    }

    @Override
    public Optional<User> update(User user) {
        int id = user.getId();
        if (Objects.isNull(id)) {
            throw new RuntimeException("기존에 존재하던 아이디가 없습니다"+ id);
        }
        User updated = USER.replace(id, user);
        return Optional.ofNullable(updated);
    }

    @Override
    public void delete(Integer id) {
        if (Objects.isNull(USER.get(id))) {
            throw new RuntimeException("기존에 해당 아이디를 가진 유저가 없습니다."+id);
        }
        USER.remove(id);
    }
}
