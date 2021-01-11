plugins {
    `java-library`
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.cloud:spring-cloud-starter-openfeign")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    api("org.mapstruct:mapstruct:1.4.1.Final")
}

configurations {
    testImplementation {
        extendsFrom(compileOnly.get())
    }
}