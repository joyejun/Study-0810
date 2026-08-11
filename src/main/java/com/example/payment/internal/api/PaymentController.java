package com.example.payment.internal.api;

import com.example.payment.Application.payment.IPaymentApplication;
import com.example.payment.Repository.Payment.Payment;
import com.example.payment.Repository.Payment.PaymentRepository;
import com.example.payment.Repository.Payment.PaymentStatus;
import com.example.payment.Repository.product.Product;
import com.example.payment.Repository.product.ProductRepository;
import com.example.payment.internal.api.dto.PaymentCreateRequestDto;
import com.example.payment.internal.api.dto.PaymentResponseDto;
import com.example.payment.internal.api.dto.ProductResponseDto;
import com.example.payment.internal.api.dto.RequestingUserDto;
import com.example.payment.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PaymentController {
  /**
   * Hexagonal (Port and Adaptor) 아키텍쳐 도입 시
   * - Controller <= Primary Adaptor = Driving Adaptor
   * - Application 인터페이스 <= Input Port
   * - Repository 인터페이스 <= Output Port
   * - Repository 구체클래스 <= Secondary Adaptor = Driven Adaptor
   */
  private final IPaymentApplication paymentApplication;

  @RequestMapping(method = RequestMethod.POST, value = "/internal/api/payments")
  public PaymentResponseDto payment(@RequestBody PaymentCreateRequestDto request) {
    Integer requestedUserId = request.getRequestUserId();
    List<Integer> productIds = request.getProductIds();
    return paymentApplication.payment(productIds, requestedUserId);
  }

  @RequestMapping(method = RequestMethod.PATCH, value = "/internal/api/payments/{id}/cancel")
  public PaymentResponseDto cancel(@PathVariable Integer id, @RequestBody RequestingUserDto request) {
    Integer requestedUserId = request.getRequestUserId();
    return paymentApplication.cancel(id, requestedUserId);
  }
}

