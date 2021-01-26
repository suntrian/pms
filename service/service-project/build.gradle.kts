
dependencies {
    implementation(project(":module:module-commons"))
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("mysql:mysql-connector-java:8.0.21")
    implementation("org.flywaydb:flyway-core:7.0.0-beta1")
    //implementation("org.liquibase:liquibase-core:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation(project(":interface"))
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    //implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    compileOnly("org.projectlombok:lombok")
    implementation("org.springframework.kafka:spring-kafka:2.6.1")
    testImplementation("org.springframework.kafka:spring-kafka-test:2.6.1")
}
