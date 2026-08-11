package com.example.payment.Application.product;

import com.example.payment.internal.api.dto.ProductResponseDto;

import java.util.List;

public interface IProductApplication {
    List<ProductResponseDto> retrieve();
    ProductResponseDto retrieve(Integer id);

}
