package com.example.payment.Repository.Payment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum PaymentStatus {
    IN_PAYMENT("결재중",true),
    PAYMENT_COMPLETE("결재완료",true),
    IN_DELIVERY("배송중",false),
    DELIVERY_COMPLETE("배송완료",false),
    CANCAL_COMPLETE("취소완료", false);

    String description;
    boolean cancellble;
}
