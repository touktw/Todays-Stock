object Libraries {
    const val Kotlin = "org.jetbrains.kotlin:kotlin-sdtlib-jdk7:${Versions.Kotlin}"

    const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"

    const val Material = "com.google.android.material:material:${Versions.Material}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"

    const val Junit = "junit:junit:${Versions.Junit}"
    const val AndroidJunit = "androidx.test.ext:junit:${Versions.AndroidJunit}"
    const val AndroidEspresso = "androidx.test.espresso:espresso-core:${Versions.AndroidEspresso}"

    val androidCore = listOf(
        CoreKtx,
        AppCompat
    )

    val ui = listOf(
        Material,
        ConstraintLayout
    )
}