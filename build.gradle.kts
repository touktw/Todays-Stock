// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.version.catalog.update)
    id("com.github.ben-manes.versions") version "0.41.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}