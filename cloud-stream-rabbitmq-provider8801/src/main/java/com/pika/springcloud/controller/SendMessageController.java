package com.pika.springcloud.controller;

import com.pika.springcloud.service.IMassageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private IMassageProvider massageProvider;
    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return massageProvider.send();
    }
}
