object Library {

    object Core {
        const val coreKtx = "androidx.core:core-ktx:1.12.0"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.6.1"
    }

    object Navigation {
        const val navigationCompose =
            "androidx.navigation:navigation-compose:2.7.6"
        const val accompanistNavigationAnimatio =
            "com.google.accompanist:accompanist-navigation-animation:0.34.0"
    }

    object Accompanist {
        const val accompanistPermissions = "com.google.accompanist:accompanist-permissions:0.34.0"
    }

    object Platform {
        const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.8.0"
        const val composeBom = "androidx.compose:compose-bom:2024.01.00"
    }

    object Plugins {
        const val gradle = "com.android.tools.build:gradle:8.2.2"
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22"
    }

    object Compose {
        const val activityCompose =
            "androidx.activity:activity-compose:1.8.2"
        const val ui = "androidx.compose.ui:ui:1.6.0"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:1.6.0"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:1.6.0"
        const val material3 = "androidx.compose.material3:material3:1.1.2"
        const val material = "androidx.compose.material:material:1.6.0"
    }

    object Material {
        const val googleMaterial = "com.google.android.material:material:1.11.0"
    }

    object Tests {
        const val junit = "junit:junit:4.13.2"
        const val testExtJunit = "androidx.test.ext:junit:1.1.5"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:3.5.1"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
    }

    object HiltDi {
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.1.0"
        const val android = "com.google.dagger:hilt-android:2.50"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:2.50"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:2.6.1"
        const val roomKtx = "androidx.room:room-ktx:2.6.1"
        const val roomCompiler = "androidx.room:room-compiler:2.6.1"
    }

    object Gson {
        const val gson = "com.google.code.gson:gson:2.10.1"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:2.5.0"
    }
}
