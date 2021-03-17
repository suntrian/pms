plugins {
    kotlin("jvm")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        //freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    //compileOnly("org.antlr:antlr4")
    implementation("org.antlr:antlr4-runtime")
    testImplementation("commons-io:commons-io:2.8.0")
}
