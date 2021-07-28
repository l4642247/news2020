package cn.nicecoder.newssys;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@MapperScan("cn.nicecoder.newssys.mapper")
public class NewssysApplication {
    private static final Logger logger = LoggerFactory.getLogger(NewssysApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(NewssysApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        logger.info("项目地址：http://" + ip + ":" + port);
        logger.info("接口文档地址: http://" + ip + ":" + port + "/doc.html");
    }
}
