package extensions

import Library
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

// Modules
val DependencyHandler.CORE
    get() = implementation(project(mapOf("path" to ":core")))

val DependencyHandler.CORE_THEME
    get() = implementation(project(mapOf("path" to ":core:theme")))


fun DependencyHandler.addHiltDi() {
    implementation(Library.HiltDi.hiltNavigationCompose)
    implementation(Library.HiltDi.hiltWork)
    kapt(Library.HiltDi.hiltCompiler)
}