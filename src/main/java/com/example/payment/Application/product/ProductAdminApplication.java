package com.example.payment.Application.product;

import com.example.payment.Controller.dto.Product.ProductAdminResponseDto;
import com.example.payment.Controller.dto.Product.ProductUpsertRequestDto;
import com.example.payment.Repository.product.Product;
import com.example.payment.internal.api.dto.ProductResponseDto;
import com.example.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductAdminApplication implements IProductApplication{
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

    public ProductAdminResponseDto create(Product entity) {
        Product create = productService.create(entity);
        return ProductAdminResponseDto.from(create);
    }

    public ProductAdminResponseDto update(Integer id, ProductUpsertRequestDto requestDto) {
        Product update = productService.getProduct(id);
        update.update(requestDto.getName(), requestDto.getPrice(), requestDto.getStock());
        Product updated = productService.update(update);
        return ProductAdminResponseDto.from(updated);
    }

    public void active(Integer id) {
        productService.active(id);
    }
    public void softDelete(Integer id) {
        productService.softDelete(id);
    }
    public void hardDelete(Integer id) {
        productService.hardDelete(id);
    }
}
