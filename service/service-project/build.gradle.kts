
dependencies {
    implementation(project(":module:module-commons"))
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation(project(":interface"))
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    compileOnly("org.projectlombok:lombok")
}
