
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java:8.0.21")
    //implementation("org.flywaydb:flyway-core:7.0.0-beta1")
    implementation("org.liquibase:liquibase-core:4.0.0")
    implementation(project(":interface"))
    compileOnly("org.projectlombok:lombok")
}