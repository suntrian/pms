plugins {
    kotlin("jvm") version "1.4.10"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":module:module-commons"))
    implementation(project(":module:module-storage"))
    implementation(project(":interface"))
    compileOnly("org.projectlombok:lombok")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
}
