package com.pika.springcloud.alibaba.service.impl;

import com.pika.springcloud.alibaba.dao.AccountDao;
import com.pika.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("--------->从account-service中更新账户信息");
//        //模拟超时异常，全局事务回滚
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountDao.decrease(userId, money);
        log.info("--------->从account-service中更新账户信息完成");
    }
}
