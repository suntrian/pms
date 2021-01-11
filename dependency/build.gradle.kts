
plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.springframework.boot:spring-boot-dependencies:2.4.1"))
    api(platform("org.springframework.cloud:spring-cloud-dependencies:2020.0.0"))
    api(platform("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2.2.3.RELEASE"))

    constraints {
        api("org.slf4j:slf4j-api:2.0.0-alpha1")
        api("org.junit.platform:junit-platform-engine:1.7.0-RC1")
        api("org.junit.jupiter:junit-jupiter:5.7.0-RC1")
        api("org.mockito:mockito-core:3.5.11")
        api("org.projectlombok:lombok:1.18.16")
        api("org.mapstruct:mapstruct-processor:1.4.1.Final")
    }

}
