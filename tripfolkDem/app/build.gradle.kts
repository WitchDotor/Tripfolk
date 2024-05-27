plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.tripfolk.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tripfolk"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug") // TODO: change to release
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui-kit"))
    implementation(project(":common-data"))
    implementation(project(":common-presentation"))
    implementation(project(":firebase"))

    // Main feature
    implementation(project(":feature-main-presentation"))
    implementation(project(":feature-map"))

    // Point of interest feature
    implementation(project(":feature-point-of-interest-data"))
    implementation(project(":feature-point-of-interest-domain"))

    // Profile feature
    implementation(project(":feature-profile-data"))
    implementation(project(":feature-profile-domain"))
    implementation(project(":feature-profile-presentation"))

    // Darkmode feature
    implementation(project(":feature-darkmode-data"))
    implementation(project(":feature-darkmode-domain"))

    // Auth feature
    implementation(project(":feature-auth-data"))
    implementation(project(":feature-auth-domain"))
    implementation(project(":feature-auth-presentation"))

    //OnBoarding feature
    implementation(project(":feature-onboarding-presentation"))
    implementation(project(":feature-onboarding-data"))
    implementation(project(":feature-onboarding-domain"))

    //Localisation feature
    implementation(project(":feature-localisation-presentation"))
    implementation(project(":feature-localisation-data"))
    implementation(project(":feature-localisation-domain"))

    // Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-compiler:2.49")
}