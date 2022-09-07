plugins {
    id("stock.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.bundles.network)
    implementation(libs.bundles.hilt)
    kapt(libs.bundles.hilt.kapt)
    implementation(libs.bundles.coroutines)

    implementation(libs.moshi.kotlin)
}