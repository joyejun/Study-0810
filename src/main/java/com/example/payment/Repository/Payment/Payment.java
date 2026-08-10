package com.example.payment.Repository.Payment;

import com.example.payment.Repository.Product.Product;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Payment {
    private static int PAYMENT_CURRNET_ID = 0;

    private static int idGenerator() {
        return ++PAYMENT_CURRNET_ID;
    }

    private Integer id;
    private List<Integer> productIds;
    private PaymentStatus status = PaymentStatus.IN_PAYMENT;
    private int paidPrice;
    private LocalDateTime  purchaseAt;
    private LocalDateTime  delieveryAt;
    private LocalDateTime  cancellAt;
    private boolean deleted = false;

    private Payment(Integer id, List<Integer> productIds, int paidPrice) {
        this.id = id;
        this.productIds = productIds;
        this.paidPrice = paidPrice;
        // this.deleted = deleted;
    }

    public static Payment create(List<Product> products) {
        int generatedId = idGenerator();
        List<Integer> productIds = products.stream()
                .map(Product::getId)
                .toList();
        int paidPrice = products.stream()
                .map(Product::getPrice)
                .reduce(0,Integer::sum);
        return new Payment(generatedId, productIds, paidPrice);
    }
}
