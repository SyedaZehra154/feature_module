plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    // ❌ removed compose plugin — network module doesn't need it
}

android {
    namespace = "com.example.core_network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    // ❌ removed buildFeatures { compose = true } — not needed here
}

dependencies {

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.main)
    implementation(libs.okhttp.logging)

    // Core Android
    implementation(libs.androidx.core.ktx)

    // Testing
    testImplementation(libs.junit)
}