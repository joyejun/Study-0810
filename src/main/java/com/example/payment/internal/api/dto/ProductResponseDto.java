package com.example.payment.internal.api.dto;

import com.example.payment.Repository.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProductResponseDto {
    private final Integer id;
    private final String name;
    private final int price;
    private final int stock;

    public static ProductResponseDto from(Product entity) {
        return new ProductResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock()
        );
    }
}
