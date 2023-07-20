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


fun DependencyHandler.addCompose() {
    implementation(Library.Compose.activityCompose)
    implementation(Library.Compose.ui)
    implementation(Library.Compose.uiGraphics)
    implementation(Library.Compose.uiToolingPreview)
    implementation(Library.Compose.material3)
    implementation(Library.Compose.material)
}

fun DependencyHandler.addHiltDi() {
    implementation(Library.HiltDi.navigationCompose)
    implementation(Library.HiltDi.android)
    kapt(Library.HiltDi.androidCompiler)
}

fun DependencyHandler.addRoomDi() {
    implementation(Library.Room.roomRuntime)
    implementation(Library.Room.roomKtx)
    kapt(Library.Room.roomCompiler)
}