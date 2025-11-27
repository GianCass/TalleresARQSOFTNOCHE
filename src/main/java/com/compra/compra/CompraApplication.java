package com.compra.compra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class CompraApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompraApplication.class, args);
    }
}
