package com.example.payment.Controller.admin.api;

import com.example.payment.Repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductRepository productRepository;
}
