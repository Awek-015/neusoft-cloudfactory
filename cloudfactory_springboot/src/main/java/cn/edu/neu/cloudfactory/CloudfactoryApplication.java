package cn.edu.neu.cloudfactory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.neu.cloudfactory.dao")
@SpringBootApplication
public class CloudfactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudfactoryApplication.class, args);
    }

}
