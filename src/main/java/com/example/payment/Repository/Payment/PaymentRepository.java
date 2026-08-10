package com.example.payment.Repository.Payment;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PaymentRepository {
    private final static Map<Integer, Payment> PAYMENT = new HashMap<>();
}
