package org.sunt.customize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableDiscoveryClient
@EnableWebFluxSecurity
@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication(scanBasePackages = {"org.sunt"}, exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class CustomizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomizeApplication.class, args);
    }

}
