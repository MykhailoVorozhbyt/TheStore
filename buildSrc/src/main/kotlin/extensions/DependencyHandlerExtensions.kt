package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

// Modules
val DependencyHandler.CORE
    get() = implementation(project(mapOf("path" to ":core")))

val DependencyHandler.CORE_THEME
    get() = implementation(project(mapOf("path" to ":core:theme")))
