subprojects {

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-security")
        //implementation("org.springframework.boot:spring-boot-starter-webflux")
        //implementation("org.springframework.boot:spring-boot-starter-jooq")
        implementation("org.springframework.boot:spring-boot-devtools")
        //implementation("org.springframework.kafka:spring-kafka:2.6.1")
        //testImplementation("org.springframework.kafka:spring-kafka-test:2.6.1")
        //implementation("org.apache.rocketmq:rocketmq-spring-boot-starter:2.1.1")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        //testImplementation("org.springframework.boot:spring-boot-test")
        testImplementation("com.h2database:h2:1.4.200")
        //有些是内部依赖的服务，并不需要对外开放，因此swagger放到各子项目中具体添加
        //implementation("io.springfox:springfox-swagger2:3.0.0")

    }

    configurations.all {

    }
}