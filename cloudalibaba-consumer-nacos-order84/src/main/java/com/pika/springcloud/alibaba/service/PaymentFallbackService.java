package com.pika.springcloud.alibaba.service;

import com.pika.springcloud.entities.CommonResult;
import com.pika.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "PaymentFallbackService------服务降级返回", new Payment(id, "error"));
    }
}
