package com.pika.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
@Mapper
public interface AccountDao {
    //扣减账余额
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
