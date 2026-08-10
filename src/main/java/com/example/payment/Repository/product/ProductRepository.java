package com.example.payment.Repository.product;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductRepository {
    private final static Map<Integer, Product> PRODUCT = new HashMap<>();

    public List<Product> findAll() {
        return PRODUCT.values().stream().toList();
    }

    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(PRODUCT.get(id));
    }

    public Optional<Product> create(Product entity) {
        int id = entity.getId();
        if (Objects.nonNull(id)) {
            throw new RuntimeException("기존에 해당하는 아이디가 이미 존재합니다." + id);
        }
        Product created = PRODUCT.put(id, entity);
        return Optional.ofNullable(created);
    }

    public void remove(Integer id) {
        if (Objects.isNull(PRODUCT.get(id))) {
            throw new RuntimeException("기존에 해당 아이디를 가진 유저가 없습니다."+id);
        }
        PRODUCT.remove(id);
    }
}
