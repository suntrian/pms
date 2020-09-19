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

    group = "org.sunt"
    version = "1.0-SNAPSHOT"

    if (project.name != "module-dependency") {
        apply(plugin = "java")
        dependencies {
            implementation(platform(project(":module-dependency")))
            testImplementation("org.junit.jupiter:junit-jupiter")
        }
    }
    if (!project.name.startsWith("module")) {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-log4j2")
        }
    }

    configurations.all {
        exclude(group = "ch.qos.logback")
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
    }

}
