import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { add("implementation", it) }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { add("kapt", it) }
}

fun DependencyHandler.testImplementationAll() {
    add("testImplementation", Libraries.Junit)
    add("androidTestImplementation", Libraries.AndroidJunit)
    add("androidTestImplementation", Libraries.AndroidEspresso)

}