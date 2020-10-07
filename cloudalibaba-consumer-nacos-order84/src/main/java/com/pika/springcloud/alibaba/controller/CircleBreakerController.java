package com.pika.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pika.springcloud.alibaba.service.PaymentService;
import com.pika.springcloud.entities.CommonResult;
import com.pika.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback") //1.什么都没配置
//    @SentinelResource(value = "fallback", fallback = "handlerFallback") //2.只配置fallback，只负责java运行时异常
//    @SentinelResource(value = "fallback", blockHandler = "blockerHandler") //3.只配置blockHandler,只负责sentinel违规
    @SentinelResource(value = "fallback", blockHandler = "blockerHandler", fallback = "handlerFallback")//4.都配置
//    @SentinelResource(value = "fallback", blockHandler = "blockerHandler", fallback = "handlerFallback", exceptionsToIgnore = {IllegalArgumentException.class}) //忽略属性
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录， 空指针异常");
        }
        return result;
    }
    //2.fallback兜底 //4
    public CommonResult<Payment> handlerFallback(Long id, Throwable te) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "兜底异常handlerFallback, 异常内容" + te.getMessage(), payment);

    }


    //3.blockHandler  //4
    public CommonResult<Payment> blockerHandler(Long id, BlockException be) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blocker-sentinel限流，无此流水：blockerException" + be.getMessage(), payment);
    }

    @Resource
    PaymentService paymentService;
    @GetMapping(value = "/consumer/openfeign/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }


}
