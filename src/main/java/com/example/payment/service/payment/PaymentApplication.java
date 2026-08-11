package com.example.payment.service.payment;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.Payment.PaymentStatus;
import com.example.payment.Repository.product.Product;
import com.example.payment.internal.api.dto.PaymentResponseDto;
import com.example.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentApplication {
    private final PaymentService paymentService;
    private final ProductService productService;

    public PaymentResponseDto payment(List<Integer> productIds , Integer requestedUserId) {
       List<Product> updatedProducts = productIds.stream()
               .map((productId) -> {
                   Product product = productService.getProduct(productId);
                   product.buyable();
                   product.decrease();
                   return product;
               })
               .toList();
       productService.update(updatedProducts);
        //실제 재품구매
        Payment creating = Payment.create(updatedProducts, requestedUserId);
        creating.complete(requestedUserId);

        Payment createdPayment = paymentService.create(creating);
        return PaymentResponseDto.builder()
                .payment(createdPayment)
                .products(updatedProducts)
                .build();
    }

    public PaymentResponseDto cancel( Integer id,  Integer requestUserId) {
        PaymentResponseDto.PaymentResponseDtoBuilder responseBuilder = PaymentResponseDto.builder();
        //1.취소하려는 결제건이 존재하는지 확인
        Payment payment = paymentService.getPayment(id);
        // 취소완료
        payment.cancel(requestUserId);
        paymentService.update(payment);
        responseBuilder.payment(payment);
        //3. 취소한 결제건에 들어있던 모든 상품들의 재고를 1증가시키며 롤백
        List<Product> products = new ArrayList<>();
        List<Integer> productIds = payment.getProductIds();
        for (Integer productId : productIds) {
            Product product = productService.getProduct(productId);
            product.increase();
            productService.update(product);
            products.add(product);
        }
        responseBuilder.products(products);
        return responseBuilder.build();
    }
}
