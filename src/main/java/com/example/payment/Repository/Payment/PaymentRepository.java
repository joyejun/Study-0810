package com.example.payment.Repository.Payment;

import com.example.payment.Repository.IRepository;
import com.example.payment.Repository.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepository implements IRepository<Integer , Payment> {
    private final static Map<Integer, Payment> PAYMENT = new HashMap<>();

    @Override
    public List<Payment> findAll() {
        return PAYMENT.values().stream().toList();
    }

    @Override
    public Optional<Payment> findById(Integer id) {
        return Optional.ofNullable(PAYMENT.get(id));
    }

    @Override
    public Optional<Payment> create(Payment entity) {
        int id = entity.getId();
        if (Objects.nonNull(id)) {
            throw new RuntimeException("기존에 해당하는 아이디가 이미 존재합니다." + id);
        }
        Payment created = PAYMENT.put(id, entity);
        return Optional.ofNullable(created);
    }

    @Override
    public Optional<Payment> update(Payment payment) {
        int id = payment.getId();
        if (Objects.isNull(id)) {
            throw new RuntimeException("업데이트 할려는 아이디가 없습니다" + id);
        }
        Payment updated = PAYMENT.replace(id, payment);
        return Optional.ofNullable(updated);
    }

    @Override
    public void delete(Integer id) {
        if (Objects.isNull(PAYMENT.get(id))) {
            throw new RuntimeException("기존에 해당 아이디를 가진 유저가 없습니다."+id);
        }
        PAYMENT.remove(id);
    }


}
