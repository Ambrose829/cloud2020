package spring.pika.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.pika.springcloud.alibaba.domain.CommonResult;
import spring.pika.springcloud.alibaba.service.StorageService;

import javax.annotation.Resource;

@RestController
public class StroageController {

    @Resource
    StorageService storageService;
//    @PostMapping(value = "/storage/decrease")
    @RequestMapping(value = "/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200, "扣减库存成功");
    }
}
