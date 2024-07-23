package com.est.smartrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmartRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartRestaurantApplication.class, args);
    }

}
