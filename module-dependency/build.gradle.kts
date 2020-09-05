
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
        api("org.junit.platform:junit-platform-engine:1.7.0-RC1")
        api("org.junit.jupiter:junit-jupiter:5.7.0-RC1")
    }
}
