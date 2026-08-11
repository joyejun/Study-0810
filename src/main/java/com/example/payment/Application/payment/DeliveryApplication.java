package com.example.payment.Application.payment;

import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.user.User;
import com.example.payment.admin.Service.User.UserService;
import com.example.payment.external.api.Dto.DeliveryResponseDto;
import com.example.payment.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryApplication{
    private final PaymentService paymentService;
    private final UserService userService;

    public DeliveryResponseDto delivery(Integer paymentId) {
        Payment payment = paymentService.getPayment(paymentId);
        payment.deliverling();
        return DeliveryResponseDto.from(payment);
    }

    public DeliveryResponseDto delivered(Integer paymentId) {
        Payment payment = paymentService.getPayment(paymentId);
        payment.delivered();
        Integer paidUserId = payment.getCreatedBy();
        User user = userService.getUser(paidUserId);
        user.earn(payment.getPaidPrice());
        return DeliveryResponseDto.from(payment);
    }
}
