package com.example.payment.Application.payment;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.product.Product;
import com.example.payment.internal.api.dto.PaymentResponseDto;
import com.example.payment.service.payment.PaymentService;
import com.example.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentApplication implements IPaymentApplication{
    private final PaymentService paymentService;
    private final ProductService productService;

    @Override
    public PaymentResponseDto payment(List<Integer> productIds) {
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
        Payment creating = Payment.create(updatedProducts);
        creating.complete();

        Payment createdPayment = paymentService.create(creating);
        return PaymentResponseDto.builder()
                .payment(createdPayment)
                .products(updatedProducts)
                .build();
    }

    @Override
    public PaymentResponseDto cancel( Integer id ) {
        PaymentResponseDto.PaymentResponseDtoBuilder responseBuilder = PaymentResponseDto.builder();
        //1.취소하려는 결제건이 존재하는지 확인
        Payment cancelPayment = paymentService.getPayment(id);
        // 취소완료
        cancelPayment.cancel();
        paymentService.update(cancelPayment);
        responseBuilder.payment(cancelPayment);
        //3. 취소한 결제건에 들어있던 모든 상품들의 재고를 1증가시키며 롤백
        List<Product> products = new ArrayList<>();
        List<Integer> productIds = cancelPayment.getProductIds();
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
