package com.example.payment.Controller.dto.Product;

import com.example.payment.Repository.product.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductAdminResponseDto {
    private final Integer id;
    private final String name;
    private final int price;
    private final int stock;
    private final boolean deleted;

    public static ProductAdminResponseDto from(Product entity) {
        return new ProductAdminResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock(),
                entity.isDeleted()
        );
    }
}
