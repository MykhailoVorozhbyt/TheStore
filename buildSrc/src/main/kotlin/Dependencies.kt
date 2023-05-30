object Dependencies {

    object AppConfig {
        const val namespace = "the.store"
        const val compileSdk = 33
        const val applicationId = "the.store"
        const val minSdk = 23
        const val targetSdk = 33
        const val versionCode = 1
        const val versionName = "1.0"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    private object LibraryVersion {
        const val lifecycle_runtime_ktx = "2.6.1"
        const val core_ktx = "1.8.0"
        const val gradle = "8.0.2"
        const val kotlin_gradle_plugin = "1.8.20"
        const val junit = "4.13.2"
        const val test_ext_junit = "4.13.2"
        const val espresso_core = "3.5.1"
        const val compose_bom = "2022.10.00"
        const val activity_compose = "1.5.1"
    }

    object Library {

        object Core {
            const val coreKtx = "androidx.core:core-ktx:${LibraryVersion.core_ktx}"
            const val lifecycleRuntimeKtx =
                "androidx.lifecycle:lifecycle-runtime-ktx:${LibraryVersion.lifecycle_runtime_ktx}"
        }

        object Platform{
            const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.8.0"
            const val composeBom = "androidx.compose:compose-bom:${LibraryVersion.compose_bom}"
        }


        object Plugins {
            const val gradle = "com.android.tools.build:gradle:${LibraryVersion.gradle}"
            const val kotlinGradlePlugin =
                "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibraryVersion.kotlin_gradle_plugin}"
        }

        object Compose {
            const val activityCompose =
                "androidx.activity:activity-compose:${LibraryVersion.activity_compose}"
            const val ui = "androidx.compose.ui:ui"
            const val uiGraphics = "androidx.compose.ui:ui-graphics"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
            const val material3 = "androidx.compose.material3:material3"
        }

        object Tests {
            const val junit = "junit:junit:${LibraryVersion.junit}"
            const val testExtJunit = "androidx.test.ext:junit:${LibraryVersion.test_ext_junit}"
            const val espressoCore =
                "androidx.test.espresso:espresso-core:${LibraryVersion.espresso_core}"
            const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
            const val uiTooling = "androidx.compose.ui:ui-tooling"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
        }
    }


}