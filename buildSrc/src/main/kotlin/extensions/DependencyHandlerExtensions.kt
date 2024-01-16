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


fun DependencyHandler.addDefaultLib() {
    implementation(Library.Core.coreKtx)
    implementation(Library.Core.appcompat)
    implementation(Library.Material.googleMaterial)
}

fun DependencyHandler.addDefaultApp() {
    implementation(Library.Core.coreKtx)
    implementation(platform(Library.Platform.kotlinBom))
    implementation(Library.Core.lifecycleRuntimeKtx)
    implementation(platform(Library.Platform.composeBom))
    implementation(Library.Material.googleMaterial)
}

fun DependencyHandler.addCompose() {
    implementation(Library.Compose.activityCompose)
    implementation(Library.Compose.ui)
    implementation(Library.Compose.uiGraphics)
    implementation(Library.Compose.uiToolingPreview)
    implementation(Library.Compose.material3)
    implementation(Library.Compose.material)
}

fun DependencyHandler.addNavigation(){
    implementation(Library.Navigation.navigationCompose)
    implementation(Library.Navigation.accompanistNavigationAnimatio)
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