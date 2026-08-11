package com.example.payment.internal.api;

import com.example.payment.Application.payment.IPaymentApplication;
import com.example.payment.Common.context.UserContext;
import com.example.payment.internal.api.dto.PaymentCreateRequestDto;
import com.example.payment.internal.api.dto.PaymentResponseDto;
import com.example.payment.internal.api.dto.RequestingUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    try (UserContext.ContextScope ignored = UserContext.withUser(requestedUserId)) {
      return paymentApplication.payment(productIds);
    }
  }

  @RequestMapping(method = RequestMethod.PATCH, value = "/internal/api/payments/{id}/cancel")
  public PaymentResponseDto cancel(@PathVariable Integer id, @RequestBody RequestingUserDto request) {
    Integer requestedUserId = request.getRequestUserId();
    try (UserContext.ContextScope ignored = UserContext.withUser(requestedUserId)) {
      return paymentApplication.cancel(id);
    }
  }
}

