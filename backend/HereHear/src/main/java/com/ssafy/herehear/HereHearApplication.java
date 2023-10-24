package com.ssafy.herehear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HereHearApplication {

    public static void main(String[] args) {
        SpringApplication.run(HereHearApplication.class, args);
    }

}
