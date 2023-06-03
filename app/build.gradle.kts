plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = Dependencies.AppConfig.namespace
    compileSdk = Dependencies.AppConfig.compileSdk

    defaultConfig {
        applicationId = Dependencies.AppConfig.applicationId
        minSdk = Dependencies.AppConfig.minSdk
        targetSdk = Dependencies.AppConfig.targetSdk
        versionCode = Dependencies.AppConfig.versionCode
        versionName = Dependencies.AppConfig.versionName

        testInstrumentationRunner = Dependencies.AppConfig.testInstrumentationRunner
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

    implementation(Dependencies.Library.Core.coreKtx)
    implementation(platform(Dependencies.Library.Platform.kotlinBom))
    implementation(Dependencies.Library.Core.lifecycleRuntimeKtx)
    implementation(Dependencies.Library.Compose.activityCompose)
    implementation(platform(Dependencies.Library.Platform.composeBom))
    implementation(Dependencies.Library.Compose.ui)
    implementation(Dependencies.Library.Compose.uiGraphics)
    implementation(Dependencies.Library.Compose.uiToolingPreview)
    implementation(Dependencies.Library.Material.material3)
    testImplementation(Dependencies.Library.Tests.junit)
    androidTestImplementation(Dependencies.Library.Tests.testExtJunit)
    androidTestImplementation(Dependencies.Library.Tests.espressoCore)
    androidTestImplementation(platform(Dependencies.Library.Platform.composeBom))
    androidTestImplementation(Dependencies.Library.Tests.uiTestJunit4)
    debugImplementation(Dependencies.Library.Tests.uiTooling)
    debugImplementation(Dependencies.Library.Tests.uiTestManifest)

    //Modules
    implementation(project(path = ":core"))
    implementation(project(path = ":core:theme"))
}