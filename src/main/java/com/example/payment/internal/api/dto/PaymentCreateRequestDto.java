package com.example.payment.internal.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PaymentCreateRequestDto extends RequestingUserDto {
    private List<Integer> productIds;

    public PaymentCreateRequestDto(List<Integer> productIds, Integer requestUserId ) {
        super(requestUserId);
        this.productIds = productIds;
    }
}
