plugins {
    kotlin("jvm") version "1.4.21"
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
    implementation(project(":module:module-formula"))
}
