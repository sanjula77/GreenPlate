plugins {
  //  alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("com.android.application")
   // id("com.google.gms.google-services")
    alias(libs.plugins.gms.google.services)
}

android {
    namespace = "com.example.greenplate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.greenplate"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)


    implementation("com.cloudinary:cloudinary-android:2.3.1")
    implementation(libs.ui)

    implementation(libs.google.accompanist.coil)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    implementation("io.ktor:ktor-client-android:2.3.6")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.6")
    implementation("io.ktor:ktor-client-logging:2.3.6")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.6")

  //  implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    implementation("org.slf4j:slf4j-android:1.7.30")

    implementation(libs.androidx.material.icons.extended.v150)

    implementation(libs.com.google.accompanist.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // Core dependencies
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Material Design
    implementation(libs.androidx.material3) // Ensure it's a stable version

    // Jetpack Compose & UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.ui.tooling.preview)

    // Animation & Images
    implementation(libs.android.lottie.compose)
    implementation(libs.coil.compose)

    // Performance & Benchmarking
    implementation(libs.androidx.benchmark.macro)

    // Google Play Services
    implementation(libs.play.services.nearby)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.foundation.android)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Debugging
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
