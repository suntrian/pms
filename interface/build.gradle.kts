plugins {
    `java-library`
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    api("org.springframework.cloud:spring-cloud-starter-openfeign")
}
