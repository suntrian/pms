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

        implementation("mysql:mysql-connector-java:8.0.21")
        //implementation("org.flywaydb:flyway-core:7.0.0-beta1")
        implementation("org.liquibase:liquibase-core:4.0.0")
        implementation("org.springframework.kafka:spring-kafka:2.6.1")
        testImplementation("org.springframework.kafka:spring-kafka-test:2.6.1")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        //testImplementation("org.springframework.boot:spring-boot-test")
        testImplementation("org.mockito:mockito-core")
        testImplementation("com.h2database:h2:1.4.200")
        //有些是内部依赖的服务，并不需要对外开放，因此swagger放到各子项目中具体添加
        //implementation("io.springfox:springfox-swagger2:3.0.0")
        compileOnly("org.projectlombok:lombok:1.18.12")

    }

    configurations.all {
        exclude(group = "ch.qos.logback")
    }
}