object Library {

    object Core {
        const val coreKtx = "androidx.core:core-ktx:1.10.1"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
        const val appcompat = "androidx.appcompat:appcompat:1.6.1"
    }

    object Navigation {
        const val navigationCompose =
            "androidx.navigation:navigation-compose:2.6.0"
        const val accompanistNavigationAnimatio =
            "com.google.accompanist:accompanist-navigation-animation:0.30.0"
    }

    object Platform {
        const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.8.0"
        const val composeBom = "androidx.compose:compose-bom:2022.10.00"
    }

    object Plugins {
        const val gradle = "com.android.tools.build:gradle:8.2.0"
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20"
    }

    object Compose {
        const val activityCompose =
            "androidx.activity:activity-compose:1.7.2"
        const val ui = "androidx.compose.ui:ui:1.4.3"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:1.4.3"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:1.4.3"
        const val material3 = "androidx.compose.material3:material3:1.1.1"
        const val material = "androidx.compose.material:material:1.4.3"
    }

    object Material {
        const val googleMaterial = "com.google.android.material:material:1.9.0"
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
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val android = "com.google.dagger:hilt-android:2.44"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:2.44"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:2.5.2"
        const val roomKtx = "androidx.room:room-ktx:2.5.2"
        const val roomCompiler = "androidx.room:room-compiler:2.5.2"
    }

    object Gson {
        const val gson = "com.google.code.gson:gson:2.10.1"
    }
}
