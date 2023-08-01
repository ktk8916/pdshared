package com.playdata.pdshared;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//@EnableAuthorizationServer
//@EnableResourceServer
@SpringBootApplication
@EnableJpaAuditing
public class PdsharedApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdsharedApplication.class, args);

    }

}
