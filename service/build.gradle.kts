subprojects {

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")
        //implementation("org.springframework.boot:spring-boot-starter-webflux")
        //implementation("org.springframework.boot:spring-boot-starter-jooq")
        implementation("org.springframework.boot:spring-boot-devtools")

        implementation("org.flywaydb:flyway-core:7.0.0-beta1")
        implementation("org.springframework.kafka:spring-kafka:2.6.1")
        testImplementation("org.springframework.kafka:spring-kafka-test:2.6.1")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        //testImplementation("org.springframework.boot:spring-boot-test")
        testImplementation("org.mockito:mockito-core")

        implementation("io.springfox:springfox-swagger2:3.0.0")
    }

    configurations.all {
        exclude(group = "ch.qos.logback")
    }
}