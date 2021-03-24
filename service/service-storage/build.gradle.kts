plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":module:module-commons"))
    implementation(project(":module:module-storage"))
    implementation(project(":interface"))
    compileOnly("org.projectlombok:lombok")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("mysql:mysql-connector-java")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter")
    //implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.2.1")
    //testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.1.4")
    implementation("org.flywaydb:flyway-core:7.0.0-beta1")
    //implementation("org.liquibase:liquibase-core:4.0.0")
}
