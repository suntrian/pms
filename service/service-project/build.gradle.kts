
dependencies {
    implementation(project(":module:module-commons"))
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation(project(":interface"))
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon")
    compileOnly("org.projectlombok:lombok")
}
