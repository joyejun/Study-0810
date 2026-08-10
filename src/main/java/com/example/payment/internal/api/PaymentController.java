package com.example.payment.internal.api;

import com.example.payment.Repository.product.Product;
import com.example.payment.Repository.product.ProductRepository;
import com.example.payment.internal.api.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/internal/api")
    public List<ProductResponseDto> retrieve() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/internal/api/{id}")
    public ProductResponseDto retrieve(@PathVariable Integer id) {
        Optional<Product> wrapproduct = productRepository.findById(id);
                 Product      product = wrapproduct
                         .orElseThrow(() -> new RuntimeException("찾으시는 아이디는 존재하지 않습니다"));
                 return ProductResponseDto.from(product);
    }

}
