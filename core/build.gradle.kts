import extensions.CORE_THEME
import extensions.addCompose
import extensions.addDefaultLib
import extensions.addHiltDi
import extensions.addRoomDi

plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    id(Plugins.parcelize)
    id(Plugins.kapt)
    id(Plugins.ksp)
    id(Plugins.hiltAndroid)
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
        jvmTarget = AppConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
}

dependencies {
    //coreKtx - appcompat - googleMaterial
    addDefaultLib()
    //COMPOSE
    addCompose()
    //HILT
    addHiltDi()
    //ROOM
    addRoomDi()

    implementation(Library.Navigation.navigationCompose)
    implementation(Library.Navigation.accompanistNavigationAnimatio)
    implementation(Library.Gson.gson)

    CORE_THEME
}