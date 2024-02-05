object AppConfig {
    const val namespace = "the.store"
    const val compileSdk = 34
    const val applicationId = "the.store"
    const val minSdk = 23
    const val targetSdk = 34
    const val versionCode = 1

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionBuild = 0
    const val versionName = "$versionMajor$versionMinor$versionPatch$versionBuild"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val kotlinCompilerExtensionVersion = "1.5.8"
}