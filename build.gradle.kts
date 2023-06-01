buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Library.Plugins.gradle)
        classpath(Dependencies.Library.Plugins.kotlinGradlePlugin)
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}