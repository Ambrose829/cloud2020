package spring.pika.springcloud.alibaba.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.pika.springcloud.alibaba.dao.StorageDao;
import spring.pika.springcloud.alibaba.service.StorageService;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("--------->从Storage-service中更新库存信息");
        storageDao.decrease(productId, count);
        log.info("--------->从Storage-service中更新库存信息完成");
    }
}
