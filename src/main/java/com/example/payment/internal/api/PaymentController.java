package com.example.payment.internal.api;

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
  private final PaymentService paymentService;
}

