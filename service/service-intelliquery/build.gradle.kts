plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.antlr:antlr4")
    implementation("org.antlr:antlr4-runtime")

    implementation(project(":module:module-query"))
    testImplementation("com.hankcs:hanlp:portable-1.8.0")
    testImplementation("com.janeluo:ikanalyzer:2012_u6")
}
