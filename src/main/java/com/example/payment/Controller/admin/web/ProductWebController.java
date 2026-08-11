package com.example.payment.Controller.admin.web;

import com.example.payment.Repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductWebController {
    private final ProductRepository productRepository;
}
