pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WBProfit"
include(":app")
//Core
include(":core:network:api")
include(":core:network:impl")
include(":core:utils:secure:impl")
include(":core:utils:secure:api")

//Feature domain
include(":feature:cards:api")
include(":feature:cards:impl")
include(":feature:auth:api")
include(":feature:auth:impl")

//Feature ui
include(":ui:card:api")
include(":ui:card:impl")
include(":ui:cards:api")
include(":ui:cards:impl")
include(":ui:main:api")
include(":ui:main:impl")
include(":ui:auth:api")
include(":ui:auth:impl")

//Base
include(":base:ui")

include(":core:keystore")
include(":core:keystore:api")
include(":core:keystore:impl")
