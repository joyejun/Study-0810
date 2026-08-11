package com.example.payment.service.product;

import com.example.payment.Application.product.IProductApplication;
import com.example.payment.Repository.IRepository;
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
    private final IRepository<Integer, Product> productRepository;


    public List<Product> getProdcts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        Optional<Product> wrapproduct = productRepository.findById(id);
        Product      product = wrapproduct
                .orElseThrow(() -> new RuntimeException("찾으시는 유저가 존재하지 않습니다."));
        return product;
    }

    public Optional<Product> findProduct(Integer id) {
        return productRepository.findById(id);
    }

    public Product create(Product entity) {
        Optional<Product> wrappedcreated = productRepository.create(entity);
                Product         product = wrappedcreated
                        .orElseThrow(() -> new RuntimeException("생성할 제품이 없습니다."));
        return product;
    }

    public List<Product> update(List<Product> entites) {
        return entites.stream()
                .map(this::update)
                .toList();
    }

    public Product update(Product entity) {
        Optional<Product> wrappedProduct = productRepository.update(entity);
                 Product         product = wrappedProduct
                         .orElseThrow(() -> new RuntimeException("업데이트가 정상적으로 진행되지 않았습니다."));
        return product;
    }

    public void active(Integer id) {
        Product exist = this.getProduct(id);
        exist.active();
        productRepository.update(exist);
    }

    public void softDelete(Integer id) {
        Product exist = this.getProduct(id);
        exist.delete();
        productRepository.update(exist);
    }

    public void hardDelete(Integer id) {
        Product exist = this.getProduct(id);
        productRepository.delete(id);
    }
}
