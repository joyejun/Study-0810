package com.example.payment.Repository.product;

import com.example.payment.Repository.Payment.Payment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;



@Repository
public class ProductRepository {
    private final static Map<Integer, Product> PRODUCT = new HashMap<>();
}
