package org.sunt.customize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableFeignClients()
@EnableWebFluxSecurity
@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
public class CustomizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomizeApplication.class, args);
    }

}
