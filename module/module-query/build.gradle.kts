plugins {
    kotlin("jvm")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.antlr:antlr4")
    implementation("org.antlr:antlr4-runtime")
    implementation("org.apache-extras.beanshell:bsh:2.0b6")
}
