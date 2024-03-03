package com.oussamamicro.notification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.oussamamicro.notification",
                "com.oussamamicro.RabbitMq",
        }
)
public class NotificationApplication {
    public static void main(String[] args){
        SpringApplication.run(NotificationApplication.class,args);
    }
}
