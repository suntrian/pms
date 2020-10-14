rootProject.name = "pms"

include("dependency")
include("module")
include("module:module-commons")
include("module:module-storage")
include("module:module-sqlanalysis")
include("module:module-multitenant")
include("module:module-formula")

include("service")
include("service:service-storage")
include("service:service-customize")
include("service:service-project")
include("service:service-search")
include("service:service-schedule")
include("service:service-auth")
include("service:service-workflow")
include("service:service-document")
include("service:service-subversion")
include("service:service-messaging")
include("service:service-git")
include("service:service-intelliquery")
include("service:service-metadata")
include("api")
include("api:api-storage")

