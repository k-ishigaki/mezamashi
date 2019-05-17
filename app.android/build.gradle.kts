plugins {
    id("com.android.application")
    kotlin("android")
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
        applicationId = "io.github.k_ishigaki.mezamashi"
        minSdkVersion(minSdkVersion)
        targetSdkVersion(targetSdkVersion)
        versionCode = mobileVersionCode
        versionName = mobileVersionName
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    packagingOptions {
        exclude("META-INF/kotlinx-coroutines-core.kotlin_module")
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }
}

dependencies {
    val kotlinVersion: String by project
    //implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
    implementation(project(":SharedCode"))
}

// Fix ClassNotFoundException:OnUnhandledKeyEventListener
configurations.all {
    resolutionStrategy.eachDependency {
        val requested = requested
        if (requested.group == "com.android.support") {
            if (!requested.name.startsWith("multidex")) {
                useVersion("26.+")
            }
        }
    }
}
