plugins {
    kotlin("jvm") version "1.4.10"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":module:module-commons"))
    implementation(project(":module:module-storage"))
    implementation(project(":interface"))
}
