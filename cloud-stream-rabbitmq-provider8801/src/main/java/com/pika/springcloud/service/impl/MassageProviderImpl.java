package com.pika.springcloud.service.impl;

import com.pika.springcloud.service.IMassageProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


//定义消息的推送管道
@EnableBinding(Source.class)
@Slf4j
public class MassageProviderImpl implements IMassageProvider {
    @Resource//消息发送管道
    private MessageChannel output;
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*******serial: " + serial);
        return null;
    }
}
