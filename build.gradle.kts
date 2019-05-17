// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlinVersion by extra("1.3.31")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

val mobileVersionCode by extra(1)
val mobileVersionName by extra("1.0.0")
val compileSdkVersion by extra(28)
val minSdkVersion by extra(21)
val targetSdkVersion by extra(28)

val kotlinVersion: String by extra

val kotlin by extra("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
