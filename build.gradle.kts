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
    id("com.google.devtools.ksp") version "1.6.10-1.0.4" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}