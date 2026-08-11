package com.example.payment.external.api.Dto;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.Payment.PaymentStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryResponseDto {
    private final Integer id;
    private final PaymentStatus status;
    private final LocalDateTime  deliveryAt;


    public static DeliveryResponseDto from(Payment entity) {
        return new DeliveryResponseDto(
                entity.getId(),
                entity.getStatus(),
                entity.getDeliveredAt()
        );
    }
}
