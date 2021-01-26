package org.sunt.storage;

import com.alibaba.cloud.nacos.ribbon.RibbonNacosAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"org.sunt"},
        exclude = {
                DataSourceAutoConfiguration.class,
                SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class,
                ManagementWebSecurityAutoConfiguration.class,
                RibbonAutoConfiguration.class,
                RibbonNacosAutoConfiguration.class
        })
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }

}
