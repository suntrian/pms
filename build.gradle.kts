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
        apply(plugin = "java" )

        dependencies {
            implementation(platform(project(":module-dependency")))
            implementation("org.springframework.boot:spring-boot-starter-log4j2")
            testImplementation("org.junit.jupiter:junit-jupiter")
        }

    }

    configurations.all {
        exclude(group = "ch.qos.logback")
    }

}
