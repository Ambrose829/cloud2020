package com.pika.springcloud.alibaba.controller;

import com.pika.springcloud.entities.CommonResult;
import com.pika.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverport;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L, new Payment(1L, "第一条信息"));
        hashMap.put(2L, new Payment(2L, "第二条信息"));
        hashMap.put(3L, new Payment(3L, "第三条信息"));
    }
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,
                "模拟从数据库中读取， 端口号：" + serverport, payment);
        return result;
    }
}
