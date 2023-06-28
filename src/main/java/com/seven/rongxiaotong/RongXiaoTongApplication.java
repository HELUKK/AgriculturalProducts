package com.seven.rongxiaotong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 声明mapper包的位置
@MapperScan("com.seven.rongxiaotong.mapper")
public class RongXiaoTongApplication {

    public static void main(String[] args) {
        SpringApplication.run(RongXiaoTongApplication.class, args);
    }

}
