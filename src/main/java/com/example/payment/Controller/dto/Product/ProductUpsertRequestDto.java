package com.example.payment.Controller.dto.Product;

import com.example.payment.Repository.product.Product;
import com.example.payment.internal.api.dto.RequestingUserDto;
import lombok.Getter;

@Getter
public class ProductUpsertRequestDto extends RequestingUserDto {
    private final String name;
    private final int price;
    private final int stock;

    public ProductUpsertRequestDto(String name, int price, int stock, Integer requestUserId) {
        super(requestUserId);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public Product to() {
        return Product.create(this.name, this.price, this.stock, super.requestUserId);
    }

}
