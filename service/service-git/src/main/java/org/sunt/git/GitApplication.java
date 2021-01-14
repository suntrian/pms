package org.sunt.git;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.sunt"})
public class GitApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitApplication.class, args);
    }

}
