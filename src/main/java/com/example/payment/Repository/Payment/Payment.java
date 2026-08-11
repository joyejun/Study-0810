package com.example.payment.Repository.Payment;

import com.example.payment.Common.context.UserContext;
import com.example.payment.Repository.BaseEntity;
import com.example.payment.Repository.product.Product;
import com.example.payment.Repository.user.User;
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

    private PaymentStatus status =PaymentStatus.IN_PAYMENT;
    private int paidPrice;

    private LocalDateTime purchasedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime cancelledAt;


    private Payment(Integer id, List<Integer> productIds, int paidPrice, Integer userId) {
        super(id, userId);
        this.productIds = productIds;
        this.paidPrice = paidPrice;
        //deleted
    }

    public static Payment create(List<Product> products) {
        int generatedId = idGenerator();
        Integer currentUserId = UserContext.getuserId();
        List<Integer> productIds = products.stream()
                .map(Product::getId)
                .toList();
        int paidPrice = products.stream()
                .map(Product::getPrice)
                .reduce(0,Integer::sum);
        return new Payment(generatedId, productIds, paidPrice, currentUserId);
    }

    public void complete() {
        //threadLocal 로부터 현재 요청한 유저 ID를 꺼내와서 권한 적용
        Integer currentUserId = UserContext.getuserId();
        if (!currentUserId.equals(super.createdBy)) {
            throw new RuntimeException("취소하려는 유저와 취소하려는 결제를 수행한 유저가 다름니다.requestuserId" +
                    currentUserId+"!= paymentUserId"+super.createdBy);
        }
        if (this.status.compareTo(PaymentStatus.PAYMENT_COMPLETE) > 0) {
            throw new RuntimeException("결제완료로 상태를 바꿀수 없는 결제건입니다. payment"+ this.toString());
        }
        this.status = PaymentStatus.PAYMENT_COMPLETE;
        this.purchasedAt = LocalDateTime.now();
        super.updated();
    }

    public void cancel() {
        //threadLocal 로부터 현재 요청한 유저 ID를 꺼내와서 권한 적용
        Integer currentUserId = UserContext.getuserId();
        if (!currentUserId.equals(super.createdBy)) {
            throw new RuntimeException("취소하려는 유저와 취소하려는 결제를 수행한 유저가 다름니다.requestuserId" +
                    currentUserId+"!= paymentUserId"+super.createdBy);
        }
        if (this.status.isCancellable()) {
            throw new RuntimeException("취소하시려는 결제는 취소할 수 없습니다- id " + this.id + "status"+ this.status);
        }
        this.status = PaymentStatus.PAYMENT_COMPLETE;
        this.purchasedAt = LocalDateTime.now();
        super.updated();
    }

    public void deliverling() {
        if (this.status.compareTo(PaymentStatus.IN_DELIVERY) > 0) {
            throw new RuntimeException("배송중으로 상태를 바굴수 없는 결재건입니다." + this.toString());
        }
        this.status = PaymentStatus.IN_DELIVERY;
        super.updated();
    }

    public void delivered() {
        if (this.status.compareTo(PaymentStatus.DELIVERY_COMPLETE) > 0) {
            throw new RuntimeException("배송완료로 상태를 바굴수 없는 결재건입니다." + this.toString());
        }
        this.status = PaymentStatus.DELIVERY_COMPLETE;
        this.deliveredAt = LocalDateTime.now();
        super.updated();
    }
}
