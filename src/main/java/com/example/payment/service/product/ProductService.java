package com.example.payment.service.product;

import com.example.payment.Repository.product.Product;
import com.example.payment.Repository.product.ProductRepository;
import com.example.payment.internal.api.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDto> retrieve() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    public ProductResponseDto retrieve(Integer id) {
        Optional<Product> wrapproduct = productRepository.findById(id);
        Product      product = wrapproduct
                .orElseThrow(() -> new RuntimeException("찾으시는 아이디는 존재하지 않습니다"));
        return ProductResponseDto.from(product);
    }
}
