package com.playdata.pdshared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableAuthorizationServer
//@EnableResourceServer
@SpringBootApplication
@EnableJpaAuditing
public class PdsharedApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdsharedApplication.class, args);

    }

}
