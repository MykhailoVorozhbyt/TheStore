buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Library.Plugins.gradle)
        classpath(Library.Plugins.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}