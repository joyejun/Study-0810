package com.example.payment.service.payment;

import com.example.payment.Application.payment.IPaymentApplication;
import com.example.payment.Repository.IRepository;
import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.Payment.PaymentRepository;
import com.example.payment.Repository.Payment.PaymentStatus;
import com.example.payment.Repository.product.Product;
import com.example.payment.Repository.product.ProductRepository;
import com.example.payment.internal.api.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * PaymentService
 *  - Service 명칭 자체가 기본적으로 도메인 서비스를 의미 Domain Service
 *      = Domain Service <- Domain Repository
 *  - Payment 도메인(엔티티 객체)을 파라미터로 받거나 반환값으로 반환하는
 *      = PaymentService 와 외부 Application 의 관계는
 *          - 외부 Application 에게 Payment 반환해주거나
 *          - 외부 Application 로부터 Payment 받아서 그것에 대한 처리를 해주거나
 *              * 처리 : CRUD 에 국한된다 / 주의 ! 도메인 내부 상태를 바꾸는 메서드는 Application 에서 호출할것 !
 *  - Domain Service 서비스의 강제사항은 단 하나의 Domain Repository 만 필드로 가져야한다는것
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final IRepository<Integer, Payment> paymentRepository;

    public Payment getPayment(Integer id) {
        Optional<Payment> wrappedPayment = paymentRepository.findById(id);
                 Payment         payment = wrappedPayment
                        .orElseThrow(() -> new RuntimeException("찾으시는 결제가 존재하지 않습니다."));

        return payment;
    }

    public Payment create(Payment entity) {
        Optional<Payment> wrappedCreated = paymentRepository.create(entity);
                    Payment       created = wrappedCreated
                            .orElseThrow(()-> new RuntimeException("결재가 정상적으로 생성되지 않습니다."));
        return created;
    }

    public Payment update(Payment entity) {
        Optional<Payment> wrappedUpdated = paymentRepository.update(entity);
        Payment                 updated = wrappedUpdated
                .orElseThrow(() -> new RuntimeException("결재가 정상적으로 업데이트 되지 않습니다."));
        return updated;
    }




}
