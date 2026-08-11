package com.example.payment.service.product;

import com.example.payment.Repository.product.Product;
import com.example.payment.internal.api.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductApplication {
    private final ProductService productService;

    public List<ProductResponseDto> retrieve() {
        List<Product> products = productService.getProdcts();
        return products.stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    public ProductResponseDto retrieve(Integer id) {
        Product product = productService.getProduct(id);
        return ProductResponseDto.from(product);
    }
}
