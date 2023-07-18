import extensions.CORE
import extensions.CORE_THEME
import extensions.addHiltDi

plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}
dependencies {

    implementation(Library.Core.coreKtx)
    implementation(platform(Library.Platform.kotlinBom))
    implementation(Library.Core.lifecycleRuntimeKtx)
    implementation(Library.Compose.activityCompose)
    implementation(platform(Library.Platform.composeBom))
    implementation(Library.Compose.ui)
    implementation(Library.Compose.uiGraphics)
    implementation(Library.Compose.uiToolingPreview)
    implementation(Library.Material.googleMaterial)
    implementation(Library.Compose.material)
    implementation(Library.Compose.material3)
    testImplementation(Library.Tests.junit)
    androidTestImplementation(Library.Tests.testExtJunit)
    androidTestImplementation(Library.Tests.espressoCore)
    androidTestImplementation(platform(Library.Platform.composeBom))
    androidTestImplementation(Library.Tests.uiTestJunit4)
    debugImplementation(Library.Tests.uiTooling)
    debugImplementation(Library.Tests.uiTestManifest)

    implementation(Library.Navigation.navigationCompose)

    //HILT
    addHiltDi()

    //Modules
    CORE
    CORE_THEME
}