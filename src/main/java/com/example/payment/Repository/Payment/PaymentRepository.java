package com.example.payment.Repository.Payment;

import com.example.payment.Repository.User.User;
import org.springframework.stereotype.Repository;

import java.util.*;
/**
 * PaymentRepository
 * : Repository 의미 자체가 저장소이니 Payment 정보에 대한 CRUD (생성, 조회, 갱신, 삭제) 제공
 *  - CRUD 모두 제공 - 결제나 배송 상태를 지속적으로 바꿔줘야하기 때문에 U 갱신 필요
 *      - R (2가지) : 전체 조회 / 단일 조회
 *      - C : 단일 생성
 *      - U : 단일 갱신
 *      - D : 단일 삭제
 */
@Repository
public class PaymentRepository {
    private static final Map<Integer, Payment> PAYMENTS = new HashMap<>();

    public List<Payment> findAll() {
        return PAYMENTS.values().stream().toList();
    }

    public Optional<Payment> findById(Integer id) {
        return Optional.ofNullable(PAYMENTS.get(id));
    }

    public Optional<Payment> create(Payment entity) {
        int id = entity.getId();
        if (Objects.nonNull(PAYMENTS.get(id))) {
            throw new RuntimeException();
        }
        Payment created = PAYMENTS.put(id, entity);
        return Optional.ofNullable(created);
    }

    public void remove(Integer id) {
        if (Objects.nonNull(PAYMENTS.get(id))) {
            throw new RuntimeException("");
        }
        PAYMENTS.remove(id);
    }
}
