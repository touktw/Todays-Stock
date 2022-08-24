import Libraries.Library
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.testImplementationAll() {
    add("testImplementation", Libraries.Junit)
    add("androidTestImplementation", Libraries.AndroidJunit)
    add("androidTestImplementation", Libraries.AndroidEspresso)
}

fun DependencyHandler.implementation(list: List<Library>) {
    list.forEach { add(it.type.typeName, it.name) }
}