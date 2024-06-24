package com.group.sshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class StationeryShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(StationeryShopApplication.class, args);
    }

}
