package com.printshopmanagement.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class PrintShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrintShopApplication.class, args);
    }

}
