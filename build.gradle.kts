plugins {
    java
}

dependencies {
    implementation(platform(project(":dependency")))
    implementation(project(":interface"))
    for (subproject in project.project(":service").subprojects) {
        implementation(project(subproject.path))
    }
}

allprojects {

    repositories {
        mavenLocal()
        maven {
            setUrl("http://maven.aliyun.com/nexus/content/groups/public/")
        }
        mavenCentral()
    }

    configurations.all {
        exclude(group = "ch.qos.logback")
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
}

subprojects {

    if (project.name != "dependency") {
        group = "org.sunt"
        version = properties["pmsVersion"]!!
        apply(plugin = "java")


        dependencies {
            implementation(platform(project(":dependency")))
            implementation("org.slf4j:slf4j-api:2.0.0-alpha1")
            implementation("org.apache.logging.log4j:log4j-api:2.13.3")
            implementation("org.apache.logging.log4j:log4j-core:2.13.3")
            implementation("org.apache.logging.log4j:log4j-slf4j18-impl:2.13.3")
            testImplementation("org.junit.jupiter:junit-jupiter")
            testImplementation("org.openjdk.jmh:jmh-core:1.26")

            annotationProcessor("org.projectlombok:lombok:1.18.16")
            annotationProcessor("org.mapstruct:mapstruct-processor:1.4.1.Final")
        }

        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
            jvmArgs("--enable-preview")
        }
    }

}