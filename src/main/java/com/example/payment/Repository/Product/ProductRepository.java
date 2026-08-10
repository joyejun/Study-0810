package com.example.payment.Repository.Product;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.User.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private static final Map<Integer, Product> PRODUCT = new HashMap<>();

    public List<Product> findAll() {
        return PRODUCT.values().stream().toList();
    }

    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(PRODUCT.get(id));
    }

    public Optional<Product> create(Product entity) {
        int id = entity.getId();
        if (Objects.nonNull(PRODUCT.get(id))) {
            throw new RuntimeException();
        }
        Product created = PRODUCT.put(id, entity);
        return Optional.ofNullable(created);
    }

    public void remove(Integer id) {
        if (Objects.nonNull(PRODUCT.get(id))) {
            throw new RuntimeException("");
        }
        PRODUCT.remove(id);
    }
}
