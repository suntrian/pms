plugins {
    java
}

allprojects {

    repositories {
        mavenLocal()
        maven {
            setUrl("http://maven.aliyun.com/nexus/content/groups/public/")
        }
        mavenCentral()
    }
}

subprojects {

    if (project.name != "dependency") {
        group = "org.sunt"
        version = "1.0-SNAPSHOT"
        apply(plugin = "java")


        dependencies {
            implementation(platform(project(":dependency")))
            implementation("org.slf4j:slf4j-api:2.0.0-alpha1")
            implementation("org.apache.logging.log4j:log4j-api:2.13.3")
            implementation("org.apache.logging.log4j:log4j-core:2.13.3")
            implementation("org.apache.logging.log4j:log4j-slf4j18-impl:2.13.3")
            testImplementation("org.junit.jupiter:junit-jupiter")
            testImplementation("org.openjdk.jmh:jmh-core:1.26")

        }

        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
            jvmArgs("--enable-preview")
        }
    }

}