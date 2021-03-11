plugins {
    kotlin("jvm") version "1.4.21"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.antlr:antlr4")
    implementation("org.antlr:antlr4-runtime")

    implementation(project(":module:module-query"))
    testImplementation("com.hankcs:hanlp:portable-1.8.0")
    
}
