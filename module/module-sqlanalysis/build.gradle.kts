plugins {
    kotlin("jvm") version "1.4.21"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("org.antlr:antlr4:4.8-1")
    implementation("org.antlr:antlr4-runtime:4.8-1")
}
