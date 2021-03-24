rootProject.name = "pms"

/**
 * @see <a href='https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_version_management'>
 */
pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
    }
}

include("dependency")
include("module")
include("module:module-commons")
include("module:module-storage")
include("module:module-sqlanalysis")
include("module:module-multitenant")
include("module:module-query")

include("service")
include("service:service-storage")
include("service:service-customize")
include("service:service-project")
include("service:service-search")
include("service:service-schedule")
include("service:service-identity")
include("service:service-workflow")
include("service:service-document")
include("service:service-subversion")
include("service:service-messaging")
include("service:service-git")
include("service:service-intelliquery")
include("service:service-metadata")
include("interface")
