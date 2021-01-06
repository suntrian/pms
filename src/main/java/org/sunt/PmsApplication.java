package org.sunt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerBeanPostProcessorAutoConfiguration;
import org.sunt.customize.CustomizeApplication;
import org.sunt.project.ProjectApplication;
import org.sunt.schedule.ScheduleApplication;
import org.sunt.storage.StorageApplication;

@SpringBootApplication(scanBasePackages = {"org.sunt"}, exclude = {LoadBalancerBeanPostProcessorAutoConfiguration.class})
public class UnitedApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UnitedApplication.class)
                .child(
                        ProjectApplication.class,
                        ScheduleApplication.class,
                        CustomizeApplication.class,
                        StorageApplication.class
                )
                .run(args);
    }

}
