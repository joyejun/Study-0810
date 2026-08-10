package com.example.payment.Repository;

import com.example.payment.Repository.Payment.Payment;

import java.util.*;

public abstract class AbstractRepository<ENTITY extends BaseEntity> implements IRepository<Integer, ENTITY>{
    private final Map<Integer, ENTITY> DATABASE = new HashMap<>();

    @Override
    public List<ENTITY> findAll() {
        return DATABASE.values().stream().toList();
    }

    @Override
    public Optional<ENTITY> findById(Integer id) {
        return Optional.ofNullable(this.DATABASE.get(id));
    }

    @Override
    public Optional<ENTITY> create(ENTITY entity) {
        int id = entity.getId();
        if (Objects.nonNull(id)) {
            throw new RuntimeException("기존에 해당하는 아이디가 이미 존재합니다." + id);
        }
        ENTITY created = DATABASE.put(id, entity);
        return Optional.ofNullable(created);
    }

    @Override
    public Optional<ENTITY> update(ENTITY payment) {
        int id = payment.getId();
        if (Objects.isNull(id)) {
            throw new RuntimeException("업데이트 할려는 아이디가 없습니다" + id);
        }
        ENTITY updated = DATABASE.replace(id, payment);
        return Optional.ofNullable(updated);
    }

    @Override
    public void delete(Integer id) {
        if (Objects.isNull(DATABASE.get(id))) {
            throw new RuntimeException("기존에 해당 아이디를 가진 유저가 없습니다."+id);
        }
        DATABASE.remove(id);
    }
}
