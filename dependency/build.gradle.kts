
plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.springframework.boot:spring-boot-dependencies:2.3.3.RELEASE"))
    api(platform("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8"))

    constraints {
        api("org.slf4j:slf4j-api:2.0.0-alpha1")
        api("org.junit.platform:junit-platform-engine:1.7.0-RC1")
        api("org.junit.jupiter:junit-jupiter:5.7.0-RC1")
        api("org.mockito:mockito-core:3.5.11")

    }
}
