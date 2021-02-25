plugins {
    kotlin("jvm") version "1.4.21"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("org.antlr:antlr4")
    implementation("org.antlr:antlr4-runtime")
}
