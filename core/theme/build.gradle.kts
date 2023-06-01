plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.example.theme"
    compileSdk = Dependencies.AppConfig.compileSdk

    defaultConfig {
        minSdk = Dependencies.AppConfig.minSdk26
        targetSdk = Dependencies.AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.Library.Core.coreKtx)
    implementation(Dependencies.Library.Core.appcompat)
    implementation(Dependencies.Library.Material.material)
    testImplementation(Dependencies.Library.Tests.junit)
    androidTestImplementation(Dependencies.Library.Tests.testExtJunit)
    androidTestImplementation(Dependencies.Library.Tests.espressoCore)
}