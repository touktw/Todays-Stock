// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version Versions.Gradle apply false
    id ("com.android.library") version Versions.Gradle apply false
    id ("org.jetbrains.kotlin.android") version Versions.Kotlin apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}