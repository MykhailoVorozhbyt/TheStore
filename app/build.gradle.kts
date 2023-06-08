plugins {
    id("com.android.application")
    kotlin("android")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(Library.Material.material3)
    testImplementation(Library.Tests.junit)
    androidTestImplementation(Library.Tests.testExtJunit)
    androidTestImplementation(Library.Tests.espressoCore)
    androidTestImplementation(platform(Library.Platform.composeBom))
    androidTestImplementation(Library.Tests.uiTestJunit4)
    debugImplementation(Library.Tests.uiTooling)
    debugImplementation(Library.Tests.uiTestManifest)

    implementation(Library.Navigation.navigationCompose)

    //Modules
    implementation(project(path = ":core"))
    implementation(project(path = ":core:theme"))
}