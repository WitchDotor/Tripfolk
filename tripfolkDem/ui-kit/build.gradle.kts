plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.tripfolk.ui_kit"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":core"))
    
    // Android native
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")

    // Compose
    api(platform("androidx.compose:compose-bom:2024.04.00"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-tooling")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended-android")
    api("io.coil-kt:coil-compose:2.5.0")
}