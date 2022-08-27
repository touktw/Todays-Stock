plugins {
    id("stock.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        applicationId = "club.qwer.stock"
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {

    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.bundles.android.ui)
    implementation(libs.bundles.network)
    implementation(libs.bundles.hilt)
    kapt(libs.bundles.hilt.kapt)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.utilities)
}