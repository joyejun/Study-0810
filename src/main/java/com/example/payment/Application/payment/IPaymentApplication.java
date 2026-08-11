package com.example.payment.Application.payment;

import com.example.payment.internal.api.dto.PaymentResponseDto;

import java.util.List;

public interface IPaymentApplication {
    PaymentResponseDto payment(List<Integer> productIds);

    PaymentResponseDto cancel(Integer id);
}
