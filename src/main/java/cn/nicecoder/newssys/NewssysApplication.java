package cn.nicecoder.newssys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.nicecoder.newssys.mapper")
public class NewssysApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewssysApplication.class, args);
    }

}
