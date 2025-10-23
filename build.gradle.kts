import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.devtools.ksp") version "2.2.20-2.0.3" apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {

    val excludedModules = setOf(
        ":base:ui",
    )

    if (project.path !in excludedModules) {

        apply(plugin = "io.gitlab.arturbosch.detekt")
        
        afterEvaluate {
            dependencies.add(
                "detektPlugins",
                "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8"
            )
        }

        extensions.configure<DetektExtension> {
            config.setFrom(files("$rootDir/detekt.yml"))
            buildUponDefaultConfig = true
            autoCorrect = true
        }

        tasks.withType<Detekt>().configureEach {
            autoCorrect = true
            config.from(files(rootDir.resolve("detekt.yml")))
            buildUponDefaultConfig = true


            reports {
                html.required.set(true)
                xml.required.set(false)
                txt.required.set(true)
            }

            setSource(files("src/main/java", "src/main/kotlin"))
            include("**/*.kt")
            exclude("**/build/**", "**/generated/**")
        }
    }
}

