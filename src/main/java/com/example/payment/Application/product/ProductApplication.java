package com.example.payment.Application.product;

import com.example.payment.Repository.product.Product;
import com.example.payment.internal.api.dto.ProductResponseDto;
import com.example.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductApplication implements IProductApplication{
    private final ProductService productService;

    @Override
    public List<ProductResponseDto> retrieve() {
        List<Product> products = productService.getProdcts();
        return products.stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    @Override
    public ProductResponseDto retrieve(Integer id) {
        Product product = productService.getProduct(id);
        return ProductResponseDto.from(product);
    }
}
