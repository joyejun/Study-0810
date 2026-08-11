package com.example.payment.internal.api;

import com.example.payment.Repository.product.Product;
import com.example.payment.Repository.product.ProductRepository;
import com.example.payment.internal.api.dto.ProductResponseDto;
import com.example.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

}
