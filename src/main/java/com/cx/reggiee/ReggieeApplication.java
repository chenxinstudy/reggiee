package com.cx.reggiee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 酷酷的鑫
 */
@Slf4j
//开启事物支持
@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
public class ReggieeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieeApplication.class, args);
        log.info("项目启动成功！");
    }

}
