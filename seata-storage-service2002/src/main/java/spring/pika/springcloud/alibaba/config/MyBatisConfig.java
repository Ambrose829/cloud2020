package spring.pika.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("spring.pika.springcloud.alibaba.dao")
public class MyBatisConfig {
}
