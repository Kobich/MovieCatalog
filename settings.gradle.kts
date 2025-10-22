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
include(":core:network:api")
include(":core:network:impl")
include(":feature:cards:api")
include(":feature:cards:impl")

include(":ui:card:api")
include(":ui:card:impl")
include(":ui:cards:api")
include(":ui:cards:impl")
include(":ui:main:api")
include(":ui:main:impl")

include(":base:ui")
