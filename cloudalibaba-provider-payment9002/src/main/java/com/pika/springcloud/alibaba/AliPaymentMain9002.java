package com.pika.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AliPaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(AliPaymentMain9002.class, args);
    }
}
