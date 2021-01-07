plugins {
    kotlin("jvm") version "1.4.21"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":module:module-commons"))
    implementation(project(":module:module-storage"))
    implementation(project(":interface"))
    compileOnly("org.projectlombok:lombok")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("mysql:mysql-connector-java:8.0.21")
    //implementation("org.flywaydb:flyway-core:7.0.0-beta1")
    implementation("org.liquibase:liquibase-core:4.0.0")
}
