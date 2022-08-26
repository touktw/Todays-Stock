object Libraries {
    object Versions {
        const val Gradle = "7.2.2"
        const val Kotlin = "1.6.10"


        // Android
        const val CoreKtx = "1.8.0"
        const val AppCompat = "1.4.2"
        const val Material = "1.6.1"
        const val ConstraintLayout = "2.1.4"
        const val ViewModel = "2.5.1"
        const val Lifecycle = "2.5.1"
        const val Activity = "1.5.1"
        const val Fragment = "1.5.2"

        // Network
        const val Retrofit = "2.9.0"
        const val Okhttp = "4.9.3"
        const val Sandwich = "1.2.7"

        // Coroutines
        const val Coroutines = "1.6.1"

        // hilt
        const val Hilt = "2.43.2"

        // room
        const val Room = "2.4.3"

        // etc
        const val Timber = "5.0.1"
        const val Moshi = "1.12.0"

        // test
        const val Junit = "4.13.2"
        const val AndroidJunit = "1.1.3"
        const val AndroidEspresso = "3.4.0"
    }

    enum class Type(val typeName: String) {
        IMPLEMENTATION("implementation"),
        KAPT("kapt"),
        TEST_IMPLEMENTATION("testImplementation"),
        ANDROID_TEST_IMPLEMENTATION("androidTestImplementation")
    }

    data class Library(
        val name: String,
        val type: Type
    )

    private val kotlin =
        Library(
            "org.jetbrains.kotlin:kotlin-sdtlib-jdk7:${Versions.Kotlin}",
            Type.IMPLEMENTATION
        )

    private val androidCore =
        Library(
            "androidx.core:core-ktx:${Versions.CoreKtx}",
            Type.IMPLEMENTATION
        )
    private val appcompat =
        Library(
            "androidx.appcompat:appcompat:${Versions.AppCompat}", Type.IMPLEMENTATION
        )
    private val viewModelKtx =
        Library(
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ViewModel}",
            Type.IMPLEMENTATION
        )
    private val lifecycleKtx =
        Library(
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle}",
            Type.IMPLEMENTATION
        )
    private val activity =
        Library(
            "androidx.activity:activity-ktx:${Versions.Activity}",
            Type.IMPLEMENTATION
        )

    private val fragment =
        Library(
            "androidx.fragment:fragment-ktx:${Versions.Fragment}",
            Type.IMPLEMENTATION
        )

    private val material =
        Library(
            "com.google.android.material:material:${Versions.Material}",
            Type.IMPLEMENTATION
        )
    private val constraintLayout =
        Library(
            "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}",
            Type.IMPLEMENTATION
        )

    private val retrofit =
        Library(
            "com.squareup.retrofit2:retrofit:${Versions.Retrofit}",
            Type.IMPLEMENTATION
        )
    private val retrofitMoshiConverter =
        Library(
            "com.squareup.retrofit2:converter-moshi:${Versions.Retrofit}",
            Type.IMPLEMENTATION
        )
    private val okhttp =
        Library(
            "com.squareup.okhttp3:okhttp:${Versions.Okhttp}",
            Type.IMPLEMENTATION
        )
    private val okhttpLogging =
        Library(
            "com.squareup.okhttp3:logging-interceptor:${Versions.Okhttp}",
            Type.IMPLEMENTATION
        )
    private val sandwich =
        Library(
            "com.github.skydoves:sandwich:${Versions.Sandwich}",
            Type.IMPLEMENTATION
        )

    // coroutines
    private val coroutines =
        Library(
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines}",
            Type.IMPLEMENTATION
        )
    private val coroutinesAndroid =
        Library(
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines}",
            Type.IMPLEMENTATION
        )

    private val moshi =
        Library(
            "com.squareup.moshi:moshi:${Versions.Moshi}",
            Type.IMPLEMENTATION
        )
    private val moshiKotlin =
        Library(
            "com.squareup.moshi:moshi-kotlin:${Versions.Moshi}",
            Type.IMPLEMENTATION
        )
    private val timber =
        Library(
            "com.jakewharton.timber:timber:${Versions.Timber}", Type.IMPLEMENTATION
        )

    const val Junit = "junit:junit:${Versions.Junit}"
    const val AndroidJunit = "androidx.test.ext:junit:${Versions.AndroidJunit}"
    const val AndroidEspresso = "androidx.test.espresso:espresso-core:${Versions.AndroidEspresso}"

    private val hiltAndroid =
        Library(
            "com.google.dagger:hilt-android:${Versions.Hilt}",
            Type.IMPLEMENTATION
        )
    private val hiltCompiler =
        Library(
            "com.google.dagger:hilt-compiler:${Versions.Hilt}",
            Type.KAPT
        )

    // room
    private val room =
        Library(
            "androidx.room:room-runtime:${Versions.Room}",
            Type.IMPLEMENTATION
        )
    private val roomKotlin =
        Library(
            "androidx.room:room-ktx:${Versions.Room}",
            Type.IMPLEMENTATION
        )
    private val roomCompiler =
        Library(
            "androidx.room:room-compiler:${Versions.Room}",
            Type.KAPT
        )

    val core = listOf(
        androidCore
    )

    val ui = listOf(
        viewModelKtx,
        material,
        constraintLayout,
        appcompat, activity, fragment,
        lifecycleKtx
    )

    val network = listOf(
        retrofit, retrofitMoshiConverter, okhttp, okhttpLogging, sandwich
    )

    val async = listOf(
        coroutines, coroutinesAndroid
    )

    val di = listOf(
        hiltAndroid, hiltCompiler
    )

    val database = listOf(
        room, roomKotlin, roomCompiler
    )

    val etc = listOf(
        moshi, moshiKotlin, timber
    )
}