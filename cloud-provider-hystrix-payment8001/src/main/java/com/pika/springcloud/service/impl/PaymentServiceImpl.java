package com.pika.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pika.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.concurrent.TimeUnit;
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    //正常访问
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_OK,id：" + id + "\t" + "😊";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeout = 3;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_TimeOut,id：" + id + "\t" + "😔" + "耗时（秒）" + timeout;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_TimeOutHandler,id：" + id + "\t" + "😔😔😔😔";
    }
    //服务降级↑

    //服务熔断↓
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("**************id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功， 流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试， 😔😔😔😔， id：" + id;
    }
}
