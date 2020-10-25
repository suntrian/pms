package org.sunt.customize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class CustomizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomizeApplication.class, args);
    }

}
