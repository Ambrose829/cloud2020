package com.pika.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pika.springcloud.entities.CommonResult;
import com.pika.springcloud.entities.Payment;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "按客户自定义限流测试,global handlerException------1");
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "按客户自定义限流测试,global handlerException------2");
    }
}
