import extensions.CORE_THEME
import extensions.addHiltDi
import extensions.addRoomDi

plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.core"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(Library.Core.coreKtx)
    implementation(Library.Core.appcompat)
    implementation(Library.Material.googleMaterial)
    testImplementation(Library.Tests.junit)
    androidTestImplementation(Library.Tests.testExtJunit)
    androidTestImplementation(Library.Tests.espressoCore)

    implementation(Library.Compose.activityCompose)
    implementation(Library.Compose.ui)
    implementation(Library.Compose.uiGraphics)
    implementation(Library.Compose.uiToolingPreview)
    implementation(Library.Compose.material)

    implementation(Library.Navigation.navigationCompose)

    //HILT
    addHiltDi()

    //ROOM
    addRoomDi()

    CORE_THEME
}