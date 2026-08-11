package com.example.payment.internal.api.dto;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.Payment.PaymentStatus;
import com.example.payment.Repository.product.Product;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentResponseDto {
    private Integer id;
    private List<Integer> productIds;
    private PaymentStatus status =PaymentStatus.IN_PAYMENT;
    private int paidPrice;
    private LocalDateTime purchasedAt;  //결제 완료 시점
    private LocalDateTime deliveredAt;  //배송 완료 시점
    private LocalDateTime cancelledAt;  //취소 완료 시점

    @Builder
    public PaymentResponseDto(Payment payment, List<Product> products) {
        this.id = payment.getId();
    }

}
