// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Libraries.Versions.Gradle apply false
    id("com.android.library") version "7.2.2" apply false
    kotlin("android") version "1.7.10" apply false
    kotlin("kapt") version Libraries.Versions.Kotlin apply false
    id("com.google.dagger.hilt.android") version Libraries.Versions.Hilt apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}