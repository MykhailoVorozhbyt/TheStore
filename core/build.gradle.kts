plugins {
    id("com.android.library")
    kotlin("android")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

}