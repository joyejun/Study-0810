package com.example.payment.external.api;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.Payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {
    private final PaymentRepository paymentRepository;
}
