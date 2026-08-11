package com.example.payment.Repository.Payment;

import com.example.payment.Repository.BaseEntity;
import com.example.payment.Repository.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString(callSuper = true)
@Getter
public class Payment extends BaseEntity {
    private static int PAYMENT_CURRENT_ID = 0;
    private static int idGenerator() {
        return ++PAYMENT_CURRENT_ID;
    }

    private List<Integer> productIds;
    @Setter
    private PaymentStatus status =PaymentStatus.IN_PAYMENT;
    private int paidPrice;
    @Setter
    private LocalDateTime purchasedAt;
    @Setter
    private LocalDateTime deliveredAt;
    @Setter
    private LocalDateTime cancelledAt;


    private Payment(Integer id, List<Integer> productIds, int paidPrice, Integer userId) {
        super(id, userId);
        this.productIds = productIds;
        this.paidPrice = paidPrice;
        //deleted
    }

    public static Payment create(List<Product> products, /**  누가 구매하였는지*/Integer userId) {
        int generatedId = idGenerator();
        List<Integer> productIds = products.stream()
                .map(Product::getId)
                .toList();
        int paidPrice = products.stream()
                .map(Product::getPrice)
                .reduce(0,Integer::sum);
        return new Payment(generatedId, productIds, paidPrice, userId);
    }

    public void updated(Integer userId) {
        setUpdatedAT(LocalDateTime.now());
        setUpdatedBy(userId);
    }
}
