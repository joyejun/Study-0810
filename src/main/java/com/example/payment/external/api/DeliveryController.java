package com.example.payment.external.api;

import com.example.payment.Application.payment.DeliveryApplication;
import com.example.payment.Repository.Payment.PaymentRepository;
import com.example.payment.external.api.Dto.DeliveryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryApplication deliveryApplication;


    @RequestMapping(method = RequestMethod.PATCH, value = "/external/api/payments/{id}/in-delivery")
    public DeliveryResponseDto delivery(@PathVariable Integer id) {
        return deliveryApplication.delivery(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/external/api/payments/{id}/delivery-complete")
    public DeliveryResponseDto deliveryed(@PathVariable Integer id) {
        return deliveryApplication.delivered(id);
    }
}
