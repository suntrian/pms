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
            testImplementation("org.junit.jupiter:junit-jupiter")
        }

        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
            jvmArgs("--enable-preview")
        }
    }

}