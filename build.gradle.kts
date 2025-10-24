import dev.detekt.gradle.Detekt
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.devtools.ksp") version "2.2.20-2.0.3" apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("dev.detekt") version libs.versions.detekt.get()
}


val detektExcludedModules = setOf(":base:ui")
val detektConfigFile = rootProject.file("detekt.yml")
val detektExcludes = listOf("**/build/**", "**/generated/**")
val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val detektKtlintWrapper = versionCatalog.findLibrary("detekt-rules-ktlint-wrapper").get()

subprojects {
    if (path in detektExcludedModules) return@subprojects
    apply(plugin = "dev.detekt")
    detekt {
        buildUponDefaultConfig = true
        autoCorrect = true
        config.setFrom(detektConfigFile)
    }

    dependencies { detektPlugins(detektKtlintWrapper) }

    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true)
            xml.required.set(false)
        }

        setSource(files("src/main/java", "src/main/kotlin"))
        include("**/*.kt")
        exclude(detektExcludes)
    }
}
