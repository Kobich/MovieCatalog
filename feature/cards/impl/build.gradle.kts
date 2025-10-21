plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.feature.cards.impl"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    //Project
    implementation(project(":feature:cards:api"))
    implementation(project(":core:network:api"))
    implementation(project(":core:network:impl"))

    //Net
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.retrofit)
    ksp(libs.moshi.kotlin.codegen)

    //DI
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
}
