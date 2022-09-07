plugins {
    `kotlin-dsl`
}

group = "club.qwer.stock.buildspec"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "stock.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "stock.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
    }
}