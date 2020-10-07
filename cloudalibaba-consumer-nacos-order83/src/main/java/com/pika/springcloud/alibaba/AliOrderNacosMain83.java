package com.pika.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AliOrderNacosMain83 {
    public static void main(String[] args) {
        SpringApplication.run(AliOrderNacosMain83.class, args);
    }
}
