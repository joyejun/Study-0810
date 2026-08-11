package com.example.payment.internal.api;

import com.example.payment.Application.product.IProductApplication;
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
    /**
     * Hexagonal (Port and Adaptor) 아키텍쳐 도입 시
     *  - Controller <= Primary Adaptor = Driving Adaptor
     *  - Application 인터페이스 <= Input Port
     *  - Repository 인터페이스 <= Output Port
     *  - Repository 구체클래스 <= Secondary Adaptor = Driven Adaptor
     */
    private final IProductApplication productApplication;

    @RequestMapping(method = RequestMethod.GET, value = "/internal/api/products")
    public List<ProductResponseDto> retrieve() {
        return productApplication.retrieve();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/internal/api/products/{id}")
    public ProductResponseDto retrieve(@PathVariable Integer id) {
        return productApplication.retrieve(id);
    }

}
