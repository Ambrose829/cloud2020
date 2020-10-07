package com.pika.springcloud.alibaba.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
