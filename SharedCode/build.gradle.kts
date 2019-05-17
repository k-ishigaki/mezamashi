import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.gradle.kotlin.dsl.execution.text
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("android.extensions")
}

android {
    val compileSdkVersion: Int by project
    val minSdkVersion: Int by project
    val targetSdkVersion: Int by project
    val mobileVersionCode: Int by project
    val mobileVersionName: String by project
    compileSdkVersion(compileSdkVersion)
    defaultConfig {
        minSdkVersion(minSdkVersion)
        targetSdkVersion(targetSdkVersion)
        versionCode = mobileVersionCode
        versionName = mobileVersionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets.forEach {
        val root = "src/androidMain/${it.name}"
        it.setRoot(root)
        it.java.srcDirs("$root/kotlin")
        it.manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}

dependencies {
    val kotlin_version: String by project
    //implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
}

kotlin {
    android()

    val ios = if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
        {name: String,  configure: KotlinNativeTarget.() -> Unit -> iosArm64(name, configure)}
    else {name: String,  configure: KotlinNativeTarget.() -> Unit -> iosX64(name, configure)}

    ios("ios") {
        binaries.framework {
            baseName = "SharedCode"
        }
    }

    sourceSets {
        val coroutinesVersion = "1.2.1"
        getByName("commonMain").dependencies {
            implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion")
        }

        getByName("androidMain").dependencies {
            implementation("org.jetbrains.kotlin:kotlin-stdlib")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        }

        getByName("iosMain").dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutinesVersion")
        }
    }
}

// workaround for https://youtrack.jetbrains.com/issue/KT-27170
configurations.create("compileClasspath")

tasks.register("packForXCode", Sync::class) {
    val frameworkDir = File(buildDir, "xcode-frameworks")
    val mode = project.findProperty("XCODE_CONFIGURATION") as String? ?: "DEBUG"
    val nativeTarget = kotlin.targets.getByName("ios") as KotlinNativeTarget
    val framework: Framework = nativeTarget.binaries.getFramework("", mode)

    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from(framework.outputFile.parentFile)
    into(frameworkDir)

    doLast {
        File(frameworkDir, "gradlew").withGroovyBuilder {
            text("#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n")
        }
    }
}

tasks.build.dependsOn("packForXCode")
