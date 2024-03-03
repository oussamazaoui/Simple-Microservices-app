package com.oussamamicro.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(
        scanBasePackages = {
                "com.oussamamicro.customer",
                "com.oussamamicro.RabbitMq",
        }
)


public class CustomerApplication {
    public static void main(String[] args){
        SpringApplication.run(CustomerApplication.class,args);
    }
}
