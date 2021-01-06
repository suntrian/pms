import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation(project(":interface"))
    implementation("com.graphql-java:graphql-java-spring-boot-starter-webflux:2020-07-12T23-24-35-c6606f6")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
}

configurations {
    implementation.get().exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}