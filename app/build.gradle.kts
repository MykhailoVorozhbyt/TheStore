import extensions.CORE
import extensions.CORE_THEME
import extensions.addCompose
import extensions.addDefaultApp
import extensions.addHiltDi
import extensions.addNavigation
import extensions.addRoomDi

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    id(Plugins.parcelize)
    id(Plugins.kapt)
    id(Plugins.ksp)
    id(Plugins.hiltAndroid)
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}
dependencies {
    testImplementation(Library.Tests.junit)
    androidTestImplementation(Library.Tests.testExtJunit)
    androidTestImplementation(Library.Tests.espressoCore)
    androidTestImplementation(platform(Library.Platform.composeBom))
    androidTestImplementation(Library.Tests.uiTestJunit4)
    debugImplementation(Library.Tests.uiTooling)
    debugImplementation(Library.Tests.uiTestManifest)

    implementation(Library.Coil.coilCompose)
    implementation(Library.Accompanist.accompanistPermissions)


    addDefaultApp()
    //Navigation
    addNavigation()
    //Compose
    addCompose()
    //HILT
    addHiltDi()
    //ROOM
    addRoomDi()
    //Modules
    CORE
    CORE_THEME
}