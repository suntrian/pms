
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("mysql:mysql-connector-java:8.0.21")
    implementation("org.flywaydb:flyway-core:7.0.0-beta1")
    implementation(project(":interface"))
    compileOnly("org.projectlombok:lombok")
}
