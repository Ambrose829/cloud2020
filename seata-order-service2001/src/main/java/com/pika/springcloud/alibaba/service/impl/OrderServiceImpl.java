package com.pika.springcloud.alibaba.service.impl;

import com.pika.springcloud.alibaba.dao.OrderDao;
import com.pika.springcloud.alibaba.domain.Order;
import com.pika.springcloud.alibaba.service.AccountService;
import com.pika.springcloud.alibaba.service.OrderService;
import com.pika.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    //name随便起保证唯一性就可以
    @GlobalTransactional(name = "pika-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--------->开始新建订单");
        orderDao.create(order);
        log.info("--------->新建订单成功");
        log.info("--------->订单微服务开始调用库存storage，做扣减");
//        log.info(order.getProductId().toString());
//        log.info(order.getCount().toString());
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("--------->订单微服务开始调用库存storage，做扣减结束");
        log.info("--------->订单微服务开始调用账户account，做扣钱");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("--------->订单微服务开始调用账户account，做扣钱结束");

        //修改订单的状态，从0到1，1表示已经完成
        log.info("--------->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("--------->修改订单状态结束");

    }
}
