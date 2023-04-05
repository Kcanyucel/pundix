package com.pundix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.pundix")
@EntityScan("com.pundix")
@SpringBootApplication
public class PundixApplication {

    public static void main(String[] args) {
        SpringApplication.run(PundixApplication.class, args);
    }
}
