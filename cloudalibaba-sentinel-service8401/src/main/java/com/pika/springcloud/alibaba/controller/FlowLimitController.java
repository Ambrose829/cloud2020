package com.pika.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        //暂停0.8秒
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info(Thread.currentThread().getName() + "\t" + "---testA");
        return "============testA";
    }
    @GetMapping("/testB")
    public String testB() {
        return "============testB";
    }

    @GetMapping("/testD")

    public String testD() {
//        //测试RT
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("testD 测试RT");
        //测试异常比例
        log.info("testD 异常比例");
        int age = 10 / 0;
        return "============testD";
    }

    @GetMapping("/testE")
    public String testE() {
        //测试异常数
        log.info("testE 异常数");
        int age = 10 / 0;
        return "============testE";
    }
    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {

        int age = 10 / 0;
        return "----------testHotKey";
    }

    public String deal_testHotKey (String p1, String p2, BlockException exception) {
        return "blockHandler : deal_testHotKey";//自定义提示
    }
}
