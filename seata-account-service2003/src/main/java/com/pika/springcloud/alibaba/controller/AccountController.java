package com.pika.springcloud.alibaba.controller;

import com.pika.springcloud.alibaba.domain.CommonResult;
import com.pika.springcloud.alibaba.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;
//    @PostMapping("/account/decrease")
    @RequestMapping(value = "/account/decrease")
    public CommonResult decrease( Long userId, BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户成功");
    }
}
