package com.pika.springcloud.dao;

import com.pika.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    //增 create add save
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
