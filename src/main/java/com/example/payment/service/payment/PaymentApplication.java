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
        PaymentResponseDto.PaymentResponseDtoBuilder responseDtoBuilder = PaymentResponseDto.builder();
        //1. 구매하려는 상품이 있는지 + 상품의 재고가 충분한지 검증
        List<Product> products = new ArrayList<>();

        for (Integer producrtId : productIds) {
            Product product = productService.getProduct(producrtId);

            if (product.getStock() < 1) {
                throw new RuntimeException("구매하시려는 상품의 재고가 존재하지 않습니다. -product"+ product);
            }
            products.add(product);
            //실제 구매완료
        }
        //실제 재품구매
        Payment creating = Payment.create(products, requestedUserId);
        creating.setStatus(PaymentStatus.PAYMENT_COMPLETE);
        creating.setPurchasedAt(LocalDateTime.now());
        creating.updated(requestedUserId);
        Payment created = paymentService.create(creating);
        responseDtoBuilder.payment(created);
        //구매가 완료된 제품에 대해서 1개씩 차감
        for (Product product : products) {
            product.setStock(product.getStock() - 1);
            productService.update(product);
        }
        responseDtoBuilder.products(products);
        return responseDtoBuilder.build();

    }

    public PaymentResponseDto cancel( Integer id,  Integer RequestedUserId) {
        PaymentResponseDto.PaymentResponseDtoBuilder responseBuilder = PaymentResponseDto.builder();
        //1.취소하려는 결제건이 존재하는지 확인
        Payment payment = paymentService.getPayment(id);
        // 취소완료
        PaymentStatus currentStatus = payment.getStatus();
        if (!currentStatus.isCancellable()) {
            throw new RuntimeException("취소하려는 결제를 취소할수 없는 상태입니다.id"+payment.getId()+"status"+currentStatus);
        }
        payment.setStatus(PaymentStatus.CANCEL_COMPLETE);
        payment.setCancelledAt(LocalDateTime.now());
        payment.updated(RequestedUserId);
        responseBuilder.payment(payment);
        //3. 취소한 결제건에 들어있던 모든 상품들의 재고를 1증가시키며 롤백
        List<Product> products = new ArrayList<>();
        List<Integer> productIds = payment.getProductIds();
        for (Integer productId : productIds) {
            Product product = productService.getProduct(productId);
            product.setStock(product.getStock() + 1);
            productService.update(product);
            products.add(product);
        }
        responseBuilder.products(products);
        return responseBuilder.build();
    }
}
