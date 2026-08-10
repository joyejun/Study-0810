package com.example.payment.Repository.Payment;

import com.example.payment.Repository.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepository {
    private final static Map<Integer, Payment> PAYMENT = new HashMap<>();

    public List<Payment> findAll() {
        return PAYMENT.values().stream().toList();
    }

    public Optional<Payment> findById(Integer id) {
        return Optional.ofNullable(PAYMENT.get(id));
    }

    public Optional<Payment> create(Payment entity) {
        int id = entity.getId();
        if (Objects.nonNull(id)) {
            throw new RuntimeException("기존에 해당하는 아이디가 이미 존재합니다." + id);
        }
        Payment created = PAYMENT.put(id, entity);
        return Optional.ofNullable(created);
    }

    public void remove(Integer id) {
        if (Objects.isNull(PAYMENT.get(id))) {
            throw new RuntimeException("기존에 해당 아이디를 가진 유저가 없습니다."+id);
        }
        PAYMENT.remove(id);
    }


}
