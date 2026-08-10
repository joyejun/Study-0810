package com.example.payment.Repository.user;

import com.example.payment.Repository.Payment.Payment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class UserRepository {
    private final static Map<Integer, User> USER = new HashMap<>();
}
