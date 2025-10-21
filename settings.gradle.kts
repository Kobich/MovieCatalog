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

rootProject.name = "Movie Catalog"
include(":app")
include(":feature")
include(":feature:cards")
include(":core")
include(":core:network")
include(":core:network:api")
include(":core:network:impl")
include(":feature:cards:api")
include(":feature:cards:impl")
