buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Library.Plugins.gradle)
        classpath(Library.Plugins.kotlinGradlePlugin)
    }
}

plugins {
    id(Plugins.ksp) version "1.9.21-1.0.15" apply false
    id(Plugins.hiltAndroid) version "2.50" apply false
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}