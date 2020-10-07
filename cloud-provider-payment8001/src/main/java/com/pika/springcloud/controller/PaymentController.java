package com.pika.springcloud.controller;

import com.pika.springcloud.entities.CommonResult;
import com.pika.springcloud.entities.Payment;
import com.pika.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @Resource
            PaymentService paymentService;
            @PostMapping(value = "/payment/create")
            //如果不使用@RequestBody会导致json型的数据插入不进来
            public CommonResult create(@RequestBody Payment payment) {
                int result = paymentService.create(payment);
                log.info("插入结果~~~~~" + result);
                if (result > 0) {
                    return new CommonResult(200, "success", result);
                } else {
                    return new CommonResult(444, "failure");
                }
            }

            @GetMapping(value = "/payment/get/{id}")
            public CommonResult getPaymentById(@PathVariable("id") Long id) {

                Payment result = paymentService.getPaymentById(id);
                log.info("查询结果~~~~~" + result);
                if (result != null) {
                    return new CommonResult(200, "success" + serverPort, result);
                } else {
                    return new CommonResult(444, "failure");
                }
            }


            @GetMapping(value = "/payment/discovery")
            public Object discovery() {
                List<String> services = discoveryClient.getServices();
                for (String element : services) {
                    log.info("*********element" + element);
                }
                List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
                for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public  String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String getPaymentFeignTimeout() {
        try {
            //暂停几秒钟线程
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "paymentZipkin返回值";
    }

}
